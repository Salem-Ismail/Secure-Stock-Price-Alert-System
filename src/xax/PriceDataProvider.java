package src.xax;
public interface PriceDataProvider {
    PriceQuote fetch(Symbol symbol);
    
}
