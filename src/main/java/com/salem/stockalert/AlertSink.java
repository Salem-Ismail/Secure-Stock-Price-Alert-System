package com.salem.stockalert;
public interface AlertSink {
    void publish(AlertEvent event);
    
}
