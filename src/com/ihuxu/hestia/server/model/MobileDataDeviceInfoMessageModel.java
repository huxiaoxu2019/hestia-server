package com.ihuxu.hestia.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MobileDataDeviceInfoMessageModel extends CommonMessageModel {

    /**
     * Constructor.
     * 
     * @param cmd {"errno":0,"errmsg":"successfully","data":{"message_type":1001,"client_key":"client_key_str","token":"aaabbbccc"}}
     */
    public MobileDataDeviceInfoMessageModel(JSONObject cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }

    public MobileDataDeviceInfoMessageModel(String cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }

    public String getClientKey() throws JSONException {
        return this.cmd.getJSONObject("data").getString("client_key");
    }
}
