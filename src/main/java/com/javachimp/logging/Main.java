package com.javachimp.logging;

public class Main {
    public static void main(String args[]) throws Exception {

        LogManager.init();
        Logger logger = LogManager.getLogger("logger1");

        int i = 0;
        while(true) {
            logger.info(String.valueOf(++i));
            Thread.sleep(5000);
            if (i ==2) break;
        }

       LogManager.shutdown();


    }
}