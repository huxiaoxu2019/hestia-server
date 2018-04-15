package com.ihuxu.hestia.server.config;

public interface CommonConfig {
    /** SERVER **/
    public static final int SERVER_SOCKET_PORT = 1722;
    public static final int SERVER_CRONTAB_CHECK_CLIENT_SOCKET_INTERVAL = 3000;
    public static final int SERVER_CRONTB_BRAIN_JOB_INTERVAL = 3000;
    public static final String SERVER_IDENTITY_TOKEN = "aaabbbccc";
    public static final String SERVER_RPI_CLIENT_KEY = "raspberry_pi_client_key";

    /** STRATEGY **/
    public static final String STRATEGY_MAP = "{"
        + "'1000':['com.ihuxu.hestia.server.library.brain.BrainLocationStrategy']"
        + "'3000':['com.ihuxu.hestia.server.library.brain.BrainDeviceInfoStrategy']"
        + "'3001':['com.ihuxu.hestia.server.library.brain.BrainHomeDeviceInfoStrategy']"
        + "}";

    /** INSTAPUSH **/
    public static String INSTAPUSH_TOKEN = "123456789";
    public static String INSTAPUSH_APP_ID = "123456789";
    public static String INSTAPUSH_APP_SECRET = "123456789";

    /** BAIDU MAP **/
    public static String BAIDU_MAP_AK = "123456789";
}
