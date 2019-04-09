package com.javachimp.logging;

import com.javachimp.logging.file.FileLogger;

public class Main {
    public static void main(String args[]) {

        LogManager.init();
        Logger l1 = LogManager.getLogger("logger2");


        for (int i=0;i<251;i++) {
            l1.info(String.valueOf(i));
            l1.debug(String.valueOf(i));
            l1.error(String.valueOf(i));
        }

        LogManager.shutdown();

        return;
    }
}