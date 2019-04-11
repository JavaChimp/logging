package com.javachimp.logging.file;

import com.javachimp.lifecycle.LifeCycleAware;
import com.javachimp.logging.*;
import com.javachimp.logging.archive.Archivable;
import com.javachimp.logging.archive.ArchiveTask;
import com.javachimp.logging.archive.ftp.FtpArchiver;
import com.javachimp.logging.config.LoggerConfig;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

public class FileLogger extends AbstractLogger implements LifeCycleAware, Archivable {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("_yyyyMMdd_hhmmss");

    private  LoggingQueue queue;
    private  File logFile;
    private  FtpArchiver archiver;
    private  Timer archiveTimer;
    private final Object lock = new Object();

    public FileLogger(LoggerConfig config) {
        super(config.getLevel());

        try {
            this.logFile = new File(config.getLogFileDriectory() + File.separator + config.getLogFileName());
            if (!logFile.exists())
                logFile.createNewFile();
        } catch (IOException ioe) {
            throw new LoggingException(ioe);
        }

        this.queue = new LoggingQueue(new FileLogWriter(logFile, lock));
        this.archiver = new FtpArchiver(config);
        this.archiveTimer = new Timer();
        long intervalMs = config.getInterval() * 60000L;

        //Normally this is bad news and bad practice.  For the purpose of the exercise I kept it simple.
        //If ArchiveTask's constructor tried to call any methods on this object while it is still in the constructor it could create problems.
        //ArchiveTask only sets a reference so it is safe for this use.  The proper way to do this is with an initializer
        //method called outside this constructor.
        archiveTimer.scheduleAtFixedRate(new ArchiveTask(this), intervalMs, intervalMs);
    }

    @Override
    public void archive() {

        if (logFile.length() == 0L)
            return;

        String logFileName = logFile.getAbsolutePath();
        String archiveLogFileName = logFileName + dateFormatter.format(LocalDateTime.now());

        synchronized (lock) {
            try {

                logFile.renameTo(new File(archiveLogFileName));
                logFile = new File(logFileName);
                logFile.createNewFile();

                queue.reset(logFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File archiveFile = new File(archiveLogFileName);
        archiver.archive(archiveFile);
        archiveFile.delete();
    }

    @Override
    protected void log(LogLevel level, String message) {
        queue.offer(new LogMessage(level, message));
    }

    @Override
    public void shutdown() {
        queue.stop();
        archiveTimer.cancel();
    }





}
