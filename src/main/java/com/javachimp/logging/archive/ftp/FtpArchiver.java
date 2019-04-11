package com.javachimp.logging.archive.ftp;

import com.javachimp.logging.LoggingException;
import com.javachimp.logging.config.LoggerConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class FtpArchiver {

    private final String host;
    private final Integer port;
    private final String username;
    private final String password;

    public FtpArchiver (LoggerConfig config) {
        this.host = config.getHost();
        this.port = config.getPort();
        this.username = config.getUsername();
        this.password = config.getPassword();
    }

    //Full Disclosure:  This code is taken almost verbatim from an exmaple for
    // commons-net.  Since this exercise is about concureency and related performance
    // and not how to use commons-net, I found it ireelevant whether I used the exmaple code or not here.

    public void archive (File file) {
        FTPClient ftp = new FTPClient();
        try {

            ftp.connect(host, port);
            int reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new LoggingException("Exception in connecting to FTP Server");
            }

            FileInputStream isr = new FileInputStream(file);

            ftp.login(username, password);
            ftp.storeFile(file.getName(), isr);
            isr.close();

        } catch (IOException ioe) {
            throw new LoggingException(ioe);
        } finally {
            try {
                ftp.disconnect();
            } catch (IOException ioe){
                throw new LoggingException(ioe);
            }
        }
    }
}
