package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import com.ihuxu.hestia.server.model.*;

public class BrainMobileDataDeviceInfoStrategy extends BrainStrategy {

  private MobileDataDeviceInfoMessageModel dimm = null;

  /** @deprecated Use {@link #execute(String,CommonMessageModel)} instead */
  @Override
  public void execute(CommonMessageModel cmm) {
    execute(null, cmm);
  }

  @Override
  public void execute(String clientId, CommonMessageModel cmm) {
    System.out.println(
        "[BrainMobileDataDeviceInfoStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
    this.dimm = new MobileDataDeviceInfoMessageModel(cmm.getCmd());

    // Change Client Key
    if (ServerClientThreadManager.changeClientKey(clientId, this.dimm.getClientKey()) == false) {
      try {
        // If there is already one the client with the same key,
        // then return false to disconnect with the client.
        ServerClientThreadManager.getClientThread(clientId).close();
        ServerClientThreadManager.removeClientThread(clientId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
