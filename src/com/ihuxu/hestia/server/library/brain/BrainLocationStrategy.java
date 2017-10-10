package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.LocationMessageModel;

public class BrainLocationStrategy extends BrainStrategy {
    public void execute(CommonMessageModel cmm) {
        System.out.println("[BrainLocationStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        LocationMessageModel lmm = new LocationMessageModel(cmm.getCmd());
    }
}
