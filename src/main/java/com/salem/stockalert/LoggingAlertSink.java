package com.salem.stockalert;

public class LoggingAlertSink implements AlertSink {
    @Override
    public void publish(AlertEvent event){
         if (event == null){
            throw new IllegalArgumentException("event");
        }

        System.out.println("ALERT TRIGGERED: " + event);
    }
}
