package com.ihuxu.hestia.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfoMessageModel extends CommonMessageModel {

    /**
     * Constructor.
     * 
     * @param cmd {"errno":0,"errmsg}":"successfully","data":{"client_key":"client_key_str","token":"aaabbbccc"}}
     */
    public DeviceInfoMessageModel(JSONObject cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }

    public DeviceInfoMessageModel(String cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }

    public String getClientKey() throws JSONException {
        return this.cmd.getJSONObject("data").getString("client_key");
    }
}
