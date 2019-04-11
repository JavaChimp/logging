package com.javachimp.logging;

import java.util.Arrays;


//I did not want to use ordinals here, it's too brittle.
//If the order of the enums change, the level
//priorities will be corrupted if using ordinals.
public enum LogLevel {
    DEBUG(1),
    INFO(2),
    ERROR(3);

    private int threshold;

    private LogLevel(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public static LogLevel fromString(String level) {
        return LogLevel.valueOf(level);
    }
}
