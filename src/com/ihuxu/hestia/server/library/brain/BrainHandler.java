package com.ihuxu.hestia.server.library.brain;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.crontab.CrontabHandler;
import com.ihuxu.hestia.server.model.CommonMessageModel;

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
                if (BrainHandler.cmdQueue.size() <= 0) {
                    Thread.sleep(100);
                    continue;
                }
                CommonMessageModel cmm = new CommonMessageModel(BrainHandler.cmdQueue.pop());
                int messageType = cmm.getMessageType();
                JSONObject strategyJsonObjRoot = new JSONObject(CommonConfig.STRATEGY_MAP);
                JSONArray currentStrategyJsonArr = strategyJsonObjRoot.getJSONArray(Integer.toString(messageType));
                for (int i = 0; i < currentStrategyJsonArr.length(); ++i) {
                    try {
                        String strategyClassName = currentStrategyJsonArr.getString(i);
                        BrainStrategy bs = BrainHandler.getInstance().getStrategy(strategyClassName);
                        bs.execute(cmm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private BrainStrategy getStrategy(String className) {
        switch (className) {
            case "com.ihuxu.hestia.server.library.brain.BrainLocationStrategy":
                return new com.ihuxu.hestia.server.library.brain.BrainLocationStrategy();
            default:
                return null;
        }
    }
}
