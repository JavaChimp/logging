package com.javachimp.logging.config;

public interface ConfigReader<T> {
    public T read(String resourceName);
}
