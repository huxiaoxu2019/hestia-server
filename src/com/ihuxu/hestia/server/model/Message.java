package com.ihuxu.hestia.server.model;

public interface Message {
    public final int TYPE_UNKNOWN = 0;
    public final int TYPE_DO_CONNECT_FROM_IOS = 1;
    public final int TYPE_DID_CONNECT_FROM_IOS = 2;
    public final int TYPE_DID_NOT_CONNECT_FROM_IOS = 3;
}
