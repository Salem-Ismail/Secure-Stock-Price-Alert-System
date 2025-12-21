package src.xaxjava;
public interface PricePublisher {
    void subscribe(PriceSubscriber subscriber);
    void unsubscribe(PriceSubscriber subscriber);
    void publish(PriceQuote quote);
    
}
