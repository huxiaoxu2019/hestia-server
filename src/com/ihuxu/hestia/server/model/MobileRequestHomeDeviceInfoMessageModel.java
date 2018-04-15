package com.ihuxu.hestia.server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MobileRequestHomeDeviceInfoMessageModel extends CommonMessageModel {

    /**
     * Constructor.
     * 
     * @param cmd {"errno":0,"errmsg":"successfully","data":{"message_type":1100,"token":"aaabbbccc"}}
     */
    public MobileRequestHomeDeviceInfoMessageModel(JSONObject cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }

    public MobileRequestHomeDeviceInfoMessageModel(String cmd) {
        super(cmd);
        // TODO Auto-generated constructor stub
    }
}
