package com.ihuxu.hestia.server.model;

import org.json.JSONObject;

public class CommonMessageModel implements MessageModel {
    private JSONObject cmd; 

    /**
     * Constructor.
     *  
     * @param cmd {"errno":0,"errmsg}":"successfully","data":{"message_type":1000,"token":"aaabbbccc"}}
     */
    public CommonMessageModel(String cmd) {
        this.cmd = new JSONObject(cmd);
    }

    public CommonMessageModel(JSONObject cmd) {
        this.cmd = cmd;
    }

    @Override
    public int getMessageType() {
        return this.cmd.getJSONObject("data").getInt("message_type");
    }

    @Override
    public int getErrorNumber() {
        return this.cmd.getInt("errno");
    }

    @Override
    public String getErrorMessage() {
        return this.cmd.getString("errmsg");
    }

    @Override
    public JSONObject getCmd() {
        return this.cmd;
    }

    @Override
    public String getToken() {
        return this.cmd.getJSONObject("data").getString("token");
    }
}
