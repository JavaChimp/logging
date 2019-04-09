package com.javachimp.logging;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage {

    private final String text;
    private final LogLevel level;
    private final String threadName = Thread.currentThread().getName();
    private final ZonedDateTime timestamp = ZonedDateTime.now();

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSSSSZ");

    public LogMessage(LogLevel level, String text) {
        this.text = text;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%s  %s  %s  %s", dtf.format(timestamp), level.toString(), threadName, text);
    }
}
