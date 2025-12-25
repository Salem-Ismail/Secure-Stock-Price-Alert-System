package com.salem.stockalert;

public class FatalProviderException extends RuntimeException {
    public FatalProviderException(String message, Throwable cause){
        super(message, cause);
    }
    public FatalProviderException(String message){
        super(message);
    }
    
}
