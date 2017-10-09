package com.ihuxu.hestia.server.library.brain;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONObject;

import com.ihuxu.hestia.server.library.crontab.CrontabHandler;

public class BrainHandler extends Thread {
    private static BrainHandler instance;
    private static LinkedList<String> cmdQueue = new LinkedList<String>(); 

    private BrainHandler() {}

    public static BrainHandler getInstance() {
        if (BrainHandler.instance == null) {
            BrainHandler.instance = new BrainHandler();

            // Start monitor CRONTAB
            CrontabHandler.checkBrainJob();

            // Start disposer thread
            BrainHandler.instance.start();
        }
        return BrainHandler.instance;
    }

    public void pushBackCmd(String cmd) {
        BrainHandler.cmdQueue.add(cmd);
    }

    /**
     * Dispose the CMD.
     */
    public void run() {
        System.out.println("[BrainHandler]run -> starting...");
        while (true) {
            try {
                if (BrainHandler.cmdQueue.size() < 1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                String cmdStr = BrainHandler.cmdQueue.pop();
                JSONObject cmdJsonObjRoot = new JSONObject(cmdStr);
                int messageType = cmdJsonObjRoot.getJSONObject("data").getInt("message_type");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
