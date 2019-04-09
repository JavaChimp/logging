package com.javachimp.logging;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

//Thread Safe as long as the only code is to offer and take.
//Linked Blocking Queues are thread safe with the use of locks internally.

public class LoggingQueue {

    private AtomicBoolean isLogging = new AtomicBoolean(false);

    //Prevents the Supplier from being blocked.
    private final Queue<LogMessage> messages
                    = new ConcurrentLinkedQueue();

    private final LogMessageConsumer consumer = new LogMessageConsumer();
    private final LogMessageSupplier supplier = new LogMessageSupplier();
    private final LogWriter writer;

    private Thread consumerThread = new Thread(() -> consumer.consume());

    public LoggingQueue(LogWriter writer) {
        super();
        this.writer = writer;
        consumerThread.start();
        isLogging.set(true);
    }

    public void offer(LogMessage message) {
        supplier.supply(message);
    }

    public void stop() {
        this.isLogging.set(false);
    }

    public class LogMessageSupplier {
        public void supply(final LogMessage message) {
            if (isLogging.get()) {
                messages.offer(message);
                return;
            }
        }
    }

    public class LogMessageConsumer {


        public void consume() {
            while (isLogging.get()) {
                LogMessage message = messages.poll();
                if (message != null)
                    writer.write(message.toString());
            }
            while(messages.size() > 0 ) {
                LogMessage message = messages.poll();
                writer.write(message.toString());
            }
            writer.close();
        }
    }
}
