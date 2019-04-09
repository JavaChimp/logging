package com.javachimp.logging;

public abstract class AbstractLogger implements Logger {

    protected final LogLevel level;

    public AbstractLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    public final void debug(String message) {
        if (level.getThreshold() >= LogLevel.DEBUG.getThreshold())
            this.log(LogLevel.DEBUG, message);
    }

    @Override
    public final void info(String message) {
        if (level.getThreshold() >= LogLevel.INFO.getThreshold())
            this.log(LogLevel.INFO, message);
    }

    @Override
    public final void error(String message) {
        if (level.getThreshold() >= LogLevel.ERROR.getThreshold())
            this.log(LogLevel.ERROR, message);
    }

    protected abstract void log(LogLevel level, String message);

}