package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.instapush.InstapushHandler;
import com.ihuxu.hestia.server.library.map.MapHandler;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.LocationMessageModel;

public class BrainLocationStrategy extends BrainStrategy {
    public void execute(CommonMessageModel cmm) {
        System.out.println("[BrainLocationStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        LocationMessageModel lmm = new LocationMessageModel(cmm.getCmd());
        String currentLocation = MapHandler.geocoder(lmm.getLnt(), lmm.getLat())
            .getJSONObject("result").getString("formatted_address");
        InstapushHandler.sendWithlocationNoticeEvent(currentLocation);
    }
}
