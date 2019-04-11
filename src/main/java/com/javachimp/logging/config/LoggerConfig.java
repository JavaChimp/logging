package com.javachimp.logging.config;

import com.javachimp.logging.LogLevel;

import java.util.Map;

public class LoggerConfig {

    private final String name;
    private Integer  interval;
    private String   host;
    private Integer  port;
    private String   username;
    private String   password;
    private String   logFileDirectory;
    private String   logFileName;
    private LogLevel level;

    public LoggerConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getInterval() {
        return interval;
    }
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogFileDriectory() {
        return logFileDirectory;
    }
    public void setLogFileDriectory(String logFileDirectory) {
        this.logFileDirectory = logFileDirectory;
    }

    public String getLogFileName() {
        return logFileName;
    }
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public LogLevel getLevel() {
        return level;
    }
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    //The proper way to do this is with constants instead of literals.
    public static LoggerConfig fromMapEntry(Map.Entry<String, Map<String, ?>> entry) {

        LoggerConfig config = new LoggerConfig(entry.getKey());
        Map<String, ?> value = entry.getValue();
        config.setHost((String) value.get("host"));
        config.setPort((Integer) value.get("port"));
        config.setInterval((Integer) value.get("interval"));
        config.setLevel(LogLevel.fromString((String) value.get("level")));
        config.setLogFileDriectory((String) value.get("log-file-directory"));
        config.setLogFileName((String) value.get("log-file-name"));
        config.setPassword((String) value.get("password"));
        config.setUsername((String) value.get("username"));

        return config;
    }
}
