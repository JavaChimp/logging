package com.javachimp.logging.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlConfigReader implements ConfigReader <Map<String, Map<String, ?>>> {
    public Map<String, Map<String, ?>> read(String resourceName) {

        Yaml yaml = new Yaml();
        InputStream is = YamlConfigReader.class.getClassLoader()
                            .getResourceAsStream(resourceName);
        Map<String, Map<String, ?>> configs = yaml.load(is);
        return configs;
    }
}
