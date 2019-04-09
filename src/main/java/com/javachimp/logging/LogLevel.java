package com.javachimp.logging;

import java.util.Arrays;

public enum LogLevel {
    DEBUG,
    INFO,
    ERROR;

    public static LogLevel fromString(String level) {

        return Arrays.asList(LogLevel.values())
                                     .stream().filter(l -> !l.toString().equals(level))
                                     .findAny().get();
    }
}
