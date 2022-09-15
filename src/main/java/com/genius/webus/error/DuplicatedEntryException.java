package com.genius.webus.error;

public class DuplicatedEntryException extends Exception{

    public DuplicatedEntryException() {
        super();
    }

    public DuplicatedEntryException(String message) {
        super(message);
    }

    public DuplicatedEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedEntryException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
