package com.ihuxu.hestia.server.model;

import org.json.JSONObject;

public class HomeDeviceInfoMessageModel extends CommonMessageModel {

  /**
   * Constructor.
   *
   * @param cmd
   *     {"errno":0,"errmsg":"successfully","data":{"message_type":3001,"bulb_status":"","light_model_status":1,"token":"aaabbbccc"}}
   */
  public HomeDeviceInfoMessageModel(JSONObject cmd) {
    super(cmd);
    // TODO Auto-generated constructor stub
  }

  public HomeDeviceInfoMessageModel(String cmd) {
    super(cmd);
    // TODO Auto-generated constructor stub
  }
}
