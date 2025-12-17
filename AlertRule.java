public interface AlertRule {
    String id();
    Symbol symbol();
    boolean matches(PriceQuote quote);

}
