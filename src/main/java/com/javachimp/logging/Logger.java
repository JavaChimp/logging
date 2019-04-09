package com.javachimp.logging;

public interface Logger {
    void debug(String message);

    void info(String message);

    void error(String message);

    default LogWriter getWriter() {
      return new ConsoleLogWriter();
    }

}
