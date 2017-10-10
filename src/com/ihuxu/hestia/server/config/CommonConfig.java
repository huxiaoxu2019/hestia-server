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
    public static String INSTAPUSH_TOKEN = "abc";
    public static String INSTAPUSH_APP_ID = "abc";
    public static String INSTAPUSH_APP_SECRET = "abc";
}
