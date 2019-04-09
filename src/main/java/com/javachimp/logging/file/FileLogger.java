package com.javachimp.logging.file;

import com.javachimp.lifecycle.LifeCycleAware;
import com.javachimp.logging.*;

public class FileLogger extends AbstractLogger implements LifeCycleAware {

    private final LoggingQueue queue;
    private final LogWriter writer;

    public FileLogger(LogLevel level, String directoryName, String fileName) {
        super(level);
        this.writer = new FileLogWriter(directoryName, fileName);
        this.queue = new LoggingQueue(writer);

    }

    @Override
    protected void log(LogLevel level, String message) {
        queue.offer(new LogMessage(level, message));
    }

    @Override
    public void shutdown() {
        queue.stop();
    }
}
