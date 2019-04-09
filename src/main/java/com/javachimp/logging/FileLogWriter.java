package com.javachimp.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileLogWriter implements LogWriter {

    private final PrintWriter writer;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public FileLogWriter(String directoryName, String fileName) {

        try {
            File file = new File(directoryName + File.separator + fileName);
            if (!file.exists())
                file.createNewFile();
            this.writer = new PrintWriter(new FileWriter(file));
        } catch (IOException ioe) {
            throw new LoggingException(ioe);
        }
    }

    @Override
    public void write(String message) {

        try {
            writeLock.tryLock();
            writer.println(message);
            writer.flush();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized void close() {
        writer.flush();
        writer.close();
    }
}
