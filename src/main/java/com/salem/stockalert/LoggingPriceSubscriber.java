package com.salem.stockalert;

public final class LoggingPriceSubscriber implements PriceSubscriber {
    @Override
    public void onPrice(PriceQuote quote) {
        System.out.println("Got price update: " + quote);
    }
}