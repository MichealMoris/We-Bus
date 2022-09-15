package com.genius.webus.error;

public class ExpiredJwtException extends Exception{

    public ExpiredJwtException() {
    }

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredJwtException(Throwable cause) {
        super(cause);
    }

    public ExpiredJwtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
