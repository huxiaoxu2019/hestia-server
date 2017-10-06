package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.crontab.CrontabHandler;

public class BrainHandler {
    private static BrainHandler instance;

    private BrainHandler() {}

    public static BrainHandler getInstance() {
        if (BrainHandler.instance == null) {
            BrainHandler.instance = new BrainHandler();

            // Start CRONTAB
            CrontabHandler.checkBrainJob();
        }
        return BrainHandler.instance;
    }

    public void pushBackCmd(String cmd) {}
}
