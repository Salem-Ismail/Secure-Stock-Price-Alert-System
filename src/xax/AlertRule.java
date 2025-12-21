package src.xaxjava;
import Symbol;

public interface AlertRule {
    String id();
    Symbol symbol();
    boolean matches(PriceQuote quote);

}
