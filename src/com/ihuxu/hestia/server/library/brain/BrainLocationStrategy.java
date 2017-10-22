package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.instapush.InstapushHandler;
import com.ihuxu.hestia.server.library.map.MapHandler;
import com.ihuxu.hestia.server.library.server.ServerClientThread;
import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.LocationMessageModel;

public class BrainLocationStrategy extends BrainStrategy {
    private static final int PUSH_NOTIFICATION_TO_IOS_INTERVAL = 60; // 60 seconds
    private static long lastPushNotificationToIosTime;
    private LocationMessageModel lmm;

    /**
	 * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
	 */
	public void execute(CommonMessageModel cmm) {
		execute(null, cmm);
	}

	public void execute(String clientId, CommonMessageModel cmm) {
        System.out.println("[BrainLocationStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
        this.lmm = new LocationMessageModel(cmm.getCmd());

        // send notification to iOS
        this.sendNotificationToIos();

        // redirect this message to RPi
        this.redirectThisMessageToRpi();
    }

    private void redirectThisMessageToRpi() {
        ServerClientThread sct;
        try {
            sct = ServerClientThreadManager.getClientThread(CommonConfig.SERVER_RPI_CLIENT_KEY);
            sct.writeLine(lmm.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNotificationToIos() {
        if (System.currentTimeMillis() - BrainLocationStrategy.lastPushNotificationToIosTime > BrainLocationStrategy.PUSH_NOTIFICATION_TO_IOS_INTERVAL * 1000) {
            BrainLocationStrategy.lastPushNotificationToIosTime = System.currentTimeMillis();
        } else {
            return;
        }
        try {
            String currentLocation = MapHandler.geocoder(lmm.getLnt(), lmm.getLat())
                .getJSONObject("result").getString("formatted_address");
            InstapushHandler.sendWithlocationNoticeEvent(currentLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
