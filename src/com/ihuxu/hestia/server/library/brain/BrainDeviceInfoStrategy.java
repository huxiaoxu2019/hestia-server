package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.DeviceInfoMessageModel;

public class BrainDeviceInfoStrategy extends BrainStrategy {

    private DeviceInfoMessageModel dimm = null;

    /**
     * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
     */
    @Override
    public void execute(CommonMessageModel cmm) {
        execute(null, cmm);
    }

    @Override
    public void execute(String clientId, CommonMessageModel cmm) {
        System.out.println("[BrainDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        this.dimm = new DeviceInfoMessageModel(cmm.getCmd());

        // Change Client Key
        ServerClientThreadManager.changeClientKey(clientId, this.dimm.getClientKey());
    }

}
