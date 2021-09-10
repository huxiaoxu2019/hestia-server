package com.ihuxu.hestia.server.model;

import org.json.JSONObject;

public interface MessageModel {
  // These are the message type constants(such as 1000), in which
  // the first char represents the platform(iOS, center server),
  // the second char represents the message type, such as data info, controlling info,
  // the next two chars represent the order.
  public final int MESSAGE_TYPE_UNKNOWN = 0;

  /** FROM iOS * */
  public final int MESSAGE_TYPE_IOS_DATA_LOCATION = 1000;

  public final int MESSAGE_TYPE_MOBILE_REQUEST_DEVICE_INFO = 1001;

  /** FROM CENTER SERVER * */
  public final int MESSAGE_TYPE_CSERVER_DATA_SOMEWHAT = 2000;

  /** FROM RPi * */
  public final int MESSAGE_TYPE_RPI_DATA_DEVICE_INFO = 3000;

  public final int MESSAGE_TYPE_RPI_DATA_HOME_DEVICE_INFO = 3001;

  public int getMessageType();

  public int getErrorNumber();

  public String getErrorMessage();

  public JSONObject getCmd();

  public String getToken();
}
