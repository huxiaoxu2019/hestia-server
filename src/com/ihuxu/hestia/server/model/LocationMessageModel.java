package com.ihuxu.hestia.server.model;

import org.json.JSONObject;

public class LocationMessageModel extends CommonMessageModel {

    public LocationMessageModel(JSONObject cmd) {
        super(cmd);
    }

    public LocationMessageModel(String cmd) {
        super(cmd);
    }

    public double getLat() {
        return this.cmd.getJSONObject("data").getDouble("lat");
    }

    public double getLnt() {
        return this.cmd.getJSONObject("data").getDouble("lnt");
    }
}
