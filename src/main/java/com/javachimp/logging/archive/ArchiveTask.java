package com.javachimp.logging.archive;

import java.util.TimerTask;

public class ArchiveTask extends TimerTask {

    private final Archivable artifact;

    public ArchiveTask(Archivable artifact) {
        this.artifact = artifact;
    }

    @Override
    public void run() {
        artifact.archive();
    }
}