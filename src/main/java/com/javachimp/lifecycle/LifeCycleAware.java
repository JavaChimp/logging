package com.javachimp.lifecycle;

public interface LifeCycleAware {
    default void startup(){
        return;
    }
    default void shutdown() {
        return;
    }
}
