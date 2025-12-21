package src.xaxjava;
import java.math.BigDecimal;
import java.time.Instant;

import InMemoryPricePublisher;
import Symbol;

public final class Demo {
    public static void main(String[] args) {
        PricePublisher publisher = new InMemoryPricePublisher();
        PriceSubscriber subscriber = new LoggingPriceSubscriber();

        publisher.subscribe(subscriber);

        PriceQuote quote = new PriceQuote(
            new Symbol("AAPL"),
            new BigDecimal("250.12"),
            Instant.now()
        );

        publisher.publish(quote);
    }
}