package com.javachimp.logging;

import com.javachimp.logging.file.FileLogger;

public class Main {
    public static void main(String args[]) {

        Logger l = new FileLogger();

        l.info("cbcbcbc");
        return;
    }
}