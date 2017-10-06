package com.ihuxu.hestia.server.model;

public interface Message {
	// These are the message type constants(such as 1000), in which 
	// the first char represents the platform(iOS, center server),
	// the second char represents the message type, such as data info, controlling info,
	// the next two chars represent the order.
	public final int MESSAGE_TYPE_UNKNOWN = 0;
	
	/** FROM iOS **/
    public final int MESSAGE_TYPE_IOS_DATA_LOCATION = 1000;
    
    /** FROM CENTER SERVER **/
    public final int MESSAGE_TYPE_CSERVER_DATA_SOMEWHAT = 2000;
}
