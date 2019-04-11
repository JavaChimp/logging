package com.javachimp.logging.file;

import com.javachimp.logging.LogWriter;
import com.javachimp.logging.LoggingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileLogWriter implements LogWriter {

    private final PrintWriter writer;
    private File logFile;


    public FileLogWriter(File logFile) {

        this.logFile = logFile;
        try {
            this.writer = new PrintWriter(new FileWriter(logFile));
        } catch (IOException ioe) {
            throw new LoggingException(ioe);
        }
    }

    @Override
    public void write(String message) {

        synchronized(logFile) {
            writer.println(message);
            writer.flush();
        }
    }

    @Override
    public synchronized void close() {

        synchronized(logFile) {
            writer.flush();
            writer.close();
        }
    }
}

