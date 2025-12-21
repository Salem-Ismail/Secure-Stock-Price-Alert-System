package com.salem.stockalert;
public interface PriceSubscriber {
    void onPrice(PriceQuote quote);
    
}
