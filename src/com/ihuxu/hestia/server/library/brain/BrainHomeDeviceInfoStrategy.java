package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.HomeDeviceInfoMessageModel;

public class BrainHomeDeviceInfoStrategy extends BrainStrategy {

    private HomeDeviceInfoMessageModel dimm = null;

    /**
     * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
     */
    @Override
    public void execute(CommonMessageModel cmm) {
        execute(null, cmm);
    }

    @Override
    public void execute(String clientId, CommonMessageModel cmm) {
        System.out.println("[BrainHomeDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        this.dimm = new HomeDeviceInfoMessageModel(cmm.getCmd());
    }

}
