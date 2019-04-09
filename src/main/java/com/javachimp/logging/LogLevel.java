package com.javachimp.logging;

import java.util.Arrays;

public enum LogLevel {
    DEBUG(3),
    INFO(2),
    ERROR(1);

    private int threshold;

    private LogLevel(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public static LogLevel fromString(String level) {

        return Arrays.asList(LogLevel.values())
                                     .stream().filter(l -> !l.toString().equals(level))
                                     .findAny().get();
    }
}
