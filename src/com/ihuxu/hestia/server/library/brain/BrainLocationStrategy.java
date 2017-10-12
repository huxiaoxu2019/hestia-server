package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.instapush.InstapushHandler;
import com.ihuxu.hestia.server.library.map.MapHandler;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.LocationMessageModel;

public class BrainLocationStrategy extends BrainStrategy {
    private static final int PUSH_INTERVAL = 60; // 60 seconds
    private long lastPushTime;

    public void execute(CommonMessageModel cmm) {
        if (System.currentTimeMillis() - this.lastPushTime > BrainLocationStrategy.PUSH_INTERVAL * 1000) {
            this.lastPushTime = System.currentTimeMillis();
        } else {
            return;
        }
        System.out.println("[BrainLocationStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        LocationMessageModel lmm = new LocationMessageModel(cmm.getCmd());
        String currentLocation = MapHandler.geocoder(lmm.getLnt(), lmm.getLat())
            .getJSONObject("result").getString("formatted_address");
        InstapushHandler.sendWithlocationNoticeEvent(currentLocation);
    }
}
