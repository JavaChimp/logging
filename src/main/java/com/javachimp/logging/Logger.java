package com.javachimp.logging;

public interface Logger {

    public abstract void debug(String message);
    public abstract void info(String message);
    public abstract void error(String message);
}
