package com.javachimp.logging;

public abstract class AbstractLogger implements Logger {

    @Override
    public final void debug(String message) {
        this.log(LogLevel.DEBUG, message);
    }

    @Override
    public final void info(String message) {
        this.log(LogLevel.INFO, message);
    }

    @Override
    public final void error(String message) {
        this.log(LogLevel.ERROR, message);
    }

    protected abstract void log(LogLevel level, String message);

}