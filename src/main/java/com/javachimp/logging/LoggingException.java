package com.javachimp.logging;

public class LoggingException extends RuntimeException {

    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(Throwable cause) {
        super(cause);
    }

    public LoggingException(String message, Throwable cause) {
        super(message, cause);
    }
}
