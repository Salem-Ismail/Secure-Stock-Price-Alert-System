package com.salem.stockalert;
public interface PriceDataProvider {
    PriceQuote fetch(Symbol symbol);
    
}
