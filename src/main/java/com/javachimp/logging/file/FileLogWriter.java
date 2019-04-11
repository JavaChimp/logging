package com.javachimp.logging.file;

import com.javachimp.logging.LogWriter;
import com.javachimp.logging.LoggingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileLogWriter implements LogWriter {

    private PrintWriter writer;
    private final Object lock;


    public FileLogWriter(File logFile, Object lock) {

        this.lock = lock;
        try {
            this.writer = new PrintWriter(new FileWriter(logFile));
        } catch (IOException ioe) {
            throw new LoggingException(ioe);
        }
    }

    @Override
    public void write(String message) {
        synchronized(lock) {
            writer.println(message);
            writer.flush();
        }
    }

    @Override
    public synchronized void close() {
        writer.flush();
        writer.close();
    }

    @Override
    public void roll(File logFile) {
        synchronized (lock) {
            try {
                this.writer = new PrintWriter(new FileWriter(logFile));
            } catch (IOException ioe) {
                throw new LoggingException(ioe);
            }
        }
    }
}

