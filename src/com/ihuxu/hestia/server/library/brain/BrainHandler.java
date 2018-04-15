package com.ihuxu.hestia.server.library.brain;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.crontab.CrontabHandler;
import com.ihuxu.hestia.server.model.CommonMessageModel;

public class BrainHandler extends Thread {
    private static BrainHandler instance;
    private static LinkedList<LinkedList<String>> cmdQueue = new LinkedList<LinkedList<String>>(); 

    private final static int INDEX_CLIENT_ID = 0;
    private final static int INDEX_CMD = 1;

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

    public void pushBackCmd(String clientId, String cmd) {
        LinkedList<String> ll = new LinkedList<String>();
        ll.add(BrainHandler.INDEX_CLIENT_ID, clientId);
        ll.add(BrainHandler.INDEX_CMD, cmd);
        BrainHandler.cmdQueue.add(ll);
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
                LinkedList<String> ll = new LinkedList<String>();
                ll = BrainHandler.cmdQueue.pop();
                CommonMessageModel cmm = new CommonMessageModel(ll.get(BrainHandler.INDEX_CMD));
                int messageType = cmm.getMessageType();
                JSONObject strategyJsonObjRoot = new JSONObject(CommonConfig.STRATEGY_MAP);
                JSONArray currentStrategyJsonArr = strategyJsonObjRoot.getJSONArray(Integer.toString(messageType));
                for (int i = 0; i < currentStrategyJsonArr.length(); ++i) {
                    try {
                        String strategyClassName = currentStrategyJsonArr.getString(i);
                        BrainStrategy bs = BrainHandler.getInstance().getStrategy(strategyClassName);
                        bs.execute(ll.get(BrainHandler.INDEX_CLIENT_ID), cmm);
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
            case "com.ihuxu.hestia.server.library.brain.BrainDeviceInfoStrategy":
                return new com.ihuxu.hestia.server.library.brain.BrainDeviceInfoStrategy();
            case "com.ihuxu.hestia.server.library.brain.BrainHomeDeviceInfoStrategy":
                return new com.ihuxu.hestia.server.library.brain.BrainHomeDeviceInfoStrategy();
            default:
                return null;
        }
    }
}
