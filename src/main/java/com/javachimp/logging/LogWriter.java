package com.javachimp.logging;

import java.io.File;

public interface LogWriter {
    public void write(String message);
    public void close();
    default void roll(File file) {
        return ;
    }
}
