package com.javachimp.logging;

import com.javachimp.logging.LogMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

//Thread Safe as long as the only code is to offer and take.
//Linked Blocking Queues are thread safe with the use of locks internally.

public class LoggingQueue {

    private AtomicBoolean isLogging = new AtomicBoolean(false);

    //Prevents the Supplier from being blocked.
    private final BlockingQueue<LogMessage> messages
                    = new LinkedBlockingQueue();

    private final LogMessageConsumer consumer = new LogMessageConsumer();
    private final LogMessageSupplier supplier = new LogMessageSupplier();
    private final Logger logger;

    private Thread consumerThread = new Thread(() -> consumer.consume());

    public LoggingQueue(Logger logger) {
        super();
        this.logger = logger;
        consumerThread.start();
        isLogging.set(true);
    }

    public void offer(LogMessage message) {
        supplier.supply(message);
    }

    public class LogMessageSupplier {
        public void supply(final LogMessage message) {
            if (isLogging.get()) {
                messages.offer(message);
            }
        }
    }

    public class LogMessageConsumer {
        public void consume() {

            try {
                while (isLogging.get()) {
                    LogMessage message = messages.take();
                    logger.getWriter().write(message.toString());
                }
                while(messages.size() > 0 ) {
                    LogMessage message = messages.take();
                    System.out.println(message);
                }

            } catch (InterruptedException ie) {
                throw new LoggingException(ie);
            }
        }
    }
}
