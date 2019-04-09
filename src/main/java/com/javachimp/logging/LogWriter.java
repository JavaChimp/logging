package com.javachimp.logging;

public interface LogWriter {
    public void write(String message);
    public void close();
}
