package com.ihuxu.hestia.server.config;

public interface CommonConfig {
    /** SERVER **/
    public static final int SERVER_SOCKET_PORT = 1722;
    public static final int SERVER_CRONTAB_CHECK_CLIENT_SOCKET_INTERVAL = 3000;
    public static final int SERVER_CRONTB_BRAIN_JOB_INTERVAL = 3000;
    public static final String SERVER_IDENTITY_TOKEN = "aaabbbccc";

    /** STRATEGY **/
    public static final String STRATEGY_MAP = "{"
        + "'1000':['com.ihuxu.hestia.server.library.brain.BrainLocationStrategy']"
        + "}";

    /** INSTAPUSH **/
    public static String INSTAPUSH_TOKEN = "59dc40faa4c48af325d2736a";
    public static String INSTAPUSH_APP_ID = "59dc41d4a4c48aab18d2736a";
    public static String INSTAPUSH_APP_SECRET = "291dc31cbf557a057cb9eb386e52d521";
}
