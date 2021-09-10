package com.ihuxu.hestia.server.library.instapush;

import com.ihuxu.hestia.server.config.CommonConfig;
import java.io.*;

public class InstapushHandler {

  public static void sendWithlocationNoticeEvent(String formattedAddress) {
    InstapushHandler.executeScript(
        "curl -X POST  "
            + "-H \"x-instapush-appid: "
            + CommonConfig.INSTAPUSH_APP_ID
            + "\" "
            + "-H \"x-instapush-appsecret: "
            + CommonConfig.INSTAPUSH_APP_SECRET
            + "\" "
            + "-H \"Content-Type: application/json\" "
            + "-d '{\"event\":\"location_notice\",\"trackers\":{\"formatted_address\":\""
            + formattedAddress
            + "\"}}' "
            + "https://api.instapush.im/v1/post");
  }

  private static void executeScript(String... commands) {
    File tempScript = null;
    try {
      tempScript = File.createTempFile("script", null);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Writer streamWriter = null;
    try {
      streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    PrintWriter printWriter = new PrintWriter(streamWriter);

    printWriter.println("#!/bin/bash");
    for (String command : commands) {
      printWriter.println(command);
    }

    printWriter.close();
    ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
    try {
      Process p1 = pb.start();
      p1.waitFor();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
