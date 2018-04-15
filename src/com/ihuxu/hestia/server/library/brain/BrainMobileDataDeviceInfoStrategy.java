package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;

public class BrainMobileDataDeviceInfoStrategy extends BrainStrategy {

    private MobileDataDeviceInfoMessageModel dimm = null;

    /**
     * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
     */
    @Override
    public void execute(CommonMessageModel cmm) {
        execute(null, cmm);
    }

    @Override
    public void execute(String clientId, CommonMessageModel cmm) {
        System.out.println("[BrainMobileDataDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        this.dimm = new MobileDataDeviceInfoMessageModel(cmm.getCmd());

        // Change Client Key
        ServerClientThreadManager.changeClientKey(clientId, this.dimm.getClientKey());
    }

}
