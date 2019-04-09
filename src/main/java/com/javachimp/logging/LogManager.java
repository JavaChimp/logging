package com.javachimp.logging;

import com.javachimp.logging.config.LoggerConfig;
import com.javachimp.logging.config.YamlConfigReader;

import java.util.Map;
import java.util.stream.Collectors;

public class LogManager {

    private static Map<String, LoggerConfig> configuration;

    private static void init() {

        Map<String, Map<String, ?>> configs = new YamlConfigReader().read("logging-config.yml");

        Map<String,LoggerConfig> configration = configs.entrySet()
                .stream().map(LoggerConfig::fromMapEntry)
                .collect(Collectors.toMap(l->l.getName(), l->l));
    }
}
