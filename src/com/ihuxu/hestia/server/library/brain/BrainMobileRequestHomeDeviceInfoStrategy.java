package com.ihuxu.hestia.server.library.brain;

import org.json.JSONObject;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.instapush.InstapushHandler;
import com.ihuxu.hestia.server.library.map.MapHandler;
import com.ihuxu.hestia.server.library.server.ServerClientThread;
import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.LocationMessageModel;

public class BrainMobileRequestHomeDeviceInfoStrategy extends BrainStrategy {
    private MobileRequestHomeDeviceInfoMessageModel lmm;

    /**
	 * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
	 */
	public void execute(CommonMessageModel cmm) {
		execute(null, cmm);
	}

	public void execute(String clientId, CommonMessageModel cmm) {
        System.out.println("[BrainMobileRequestHomeDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString() + " client_id:" + clientId);

        this.lmm = new MobileRequestHomeDeviceInfoMessageModel(cmm.getCmd());

        // redirect this message to RPi
        this.redirectThisMessageToRpi();
    }

    private void redirectThisMessageToRpi() {
        ServerClientThread sct;
        try {
            sct = ServerClientThreadManager.getClientThread(CommonConfig.SERVER_RPI_CLIENT_KEY);
            sct.writeLine(lmm.getCmd().toString().replaceAll("\n", ""));
            System.out.println("[BrainMobileRequestHomeDeviceInfoStrategy]execute -> send cmd to Rpi:" + CommonConfig.SERVER_RPI_CLIENT_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
