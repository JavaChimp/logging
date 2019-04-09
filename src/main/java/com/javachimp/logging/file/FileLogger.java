package com.javachimp.logging.file;

import com.javachimp.logging.AbstractLogger;
import com.javachimp.logging.LogLevel;
import com.javachimp.logging.LogMessage;
import com.javachimp.logging.LoggingQueue;

public class FileLogger extends AbstractLogger {

    private final LoggingQueue queue;

    public FileLogger() {
        this.queue = new LoggingQueue(this);
    }

    @Override
    protected void log(LogLevel level, String message) {
        queue.offer(new LogMessage(level, message));
    }
}
