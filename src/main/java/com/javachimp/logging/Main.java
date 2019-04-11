package com.javachimp.logging;

public class Main {

    public static void main(String args[]) throws Exception {

        LogManager.init();

        //Logger1 is INFO
        Logger logger1 = LogManager.getLogger("logger1");
        //Logger2 is DEBUG
        Logger logger2 = LogManager.getLogger("logger2");
        //Logger3 is ERROR
        Logger logger3 = LogManager.getLogger("logger3");

        int i = 0;
        int j = 100;
        int k = 200;

        while(true) {

            logger1.info(String.valueOf(++i));
            logger2.info(String.valueOf(++j));
            logger3.info(String.valueOf(++k));

            logger1.error(String.valueOf(++i));
            logger2.error(String.valueOf(++j));
            logger3.error(String.valueOf(++k));

            logger1.debug(String.valueOf(++i));
            logger2.debug(String.valueOf(++j));
            logger3.debug(String.valueOf(++k));

            Thread.sleep(5000);
            if (i == 75) break;
        }

        LogManager.shutdown();

        return;

    }
}