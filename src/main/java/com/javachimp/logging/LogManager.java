package com.javachimp.logging;

import com.javachimp.lifecycle.LifeCycleAware;
import com.javachimp.logging.config.LoggerConfig;
import com.javachimp.logging.config.YamlConfigReader;
import com.javachimp.logging.file.FileLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public class LogManager {

    private static Map<String, LoggerConfig> configuration;
    private static List<LifeCycleAware> hooks = new ArrayList<>();
    private static Map<String, Logger> loggers = new HashMap<>();

    public static void init() {

        Map<String, Map<String, ?>> configs = new YamlConfigReader().read("logging-config.yml");

        List<LoggerConfig> configurations = configs.entrySet()
                .stream().map(LoggerConfig::fromMapEntry)
                .collect(Collectors.toList());
        for (LoggerConfig configuration : configurations) {
            loggers.put(configuration.getName(), new FileLogger(configuration));
                hooks.add((LifeCycleAware) loggers.get(configuration.getName()));


        }
    }

    public static Logger getLogger(String name) {
        return loggers.get(name);
    }

    public static void shutdown() {
        hooks.forEach(LifeCycleAware::shutdown);
    }
}
