package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.server.ServerClientThread;
import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.CommonMessageModel;
import com.ihuxu.hestia.server.model.HomeDeviceInfoMessageModel;

public class BrainHomeDeviceInfoStrategy extends BrainStrategy {

  private HomeDeviceInfoMessageModel hdimm = null;

  /** @deprecated Use {@link #execute(String,CommonMessageModel)} instead */
  @Override
  public void execute(CommonMessageModel cmm) {
    execute(null, cmm);
  }

  @Override
  public void execute(String clientId, CommonMessageModel cmm) {
    System.out.println(
        "[BrainHomeDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
    this.hdimm = new HomeDeviceInfoMessageModel(cmm.getCmd());

    // Send to mobile
    ServerClientThread sct;
    try {
      sct = ServerClientThreadManager.getClientThread(CommonConfig.SERVER_MOBILE_CLIENT_KEY);
      sct.writeLine(hdimm.getCmd().toString().replaceAll("\n", ""));
      System.out.println(
          "[BrainHomeDeviceInfoStrategy]execute -> send cmd to Mobile client:"
              + CommonConfig.SERVER_MOBILE_CLIENT_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
