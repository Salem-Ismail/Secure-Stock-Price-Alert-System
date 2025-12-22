package com.salem.stockalert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Milestone 3 runner: starts one SymbolPoller per symbol and shuts down cleanly.
 */

public final class App {
    public static void main(String[] args) {
        PricePublisher publisher = new InMemoryPricePublisher();
        publisher.subscribe(new LoggingPriceSubscriber());

        PriceDataProvider provider = new FinnhubPriceDataProvider();
        Duration interval = Duration.ofSeconds(2);

        List<SymbolPoller> pollers = List.of(
            new SymbolPoller(new Symbol("AAPL"), provider, publisher, interval),
            new SymbolPoller(new Symbol("MSFT"), provider, publisher, interval)
        );

        ExecutorService exec = Executors.newFixedThreadPool(pollers.size());
        pollers.forEach(exec::execute);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            pollers.forEach(SymbolPoller::stop);
            exec.shutdownNow(); // interrupts sleeping threads
            try {
                exec.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }));

        System.out.println("Running pollers. Press Ctrl+C to stop.");
    }
}