package com.ihuxu.hestia.server.library.crontab;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.server.ServerClientThreadManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CrontabHandler {
  private static boolean clientSockedChecked = false;
  private static boolean brainJobChecked = false;

  public static void checkBrainJob() {
    if (CrontabHandler.brainJobChecked) {
      System.out.println("brain job has already been checked.");
      return;
    } else {
      CrontabHandler.brainJobChecked = true;
    }
    Runnable runnable =
        new Runnable() {
          public void run() {
            try {
              System.out.println("[CrontabHandler]checkBrainJob -> do nothing now");
              // ...
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(
        runnable,
        CommonConfig.SERVER_CRONTAB_CHECK_CLIENT_SOCKET_INTERVAL,
        CommonConfig.SERVER_CRONTB_BRAIN_JOB_INTERVAL,
        TimeUnit.MILLISECONDS);
  }

  public static void checkClientSocket() {
    if (CrontabHandler.clientSockedChecked) {
      System.out.println("client socket has already been checked.");
      return;
    } else {
      CrontabHandler.clientSockedChecked = true;
    }
    Runnable runnable =
        new Runnable() {
          public void run() {
            try {
              System.out.println(
                  "[CrontabHandler]checkClientSocket -> the client thread count is "
                      + ServerClientThreadManager.getClientThreadsCount());
              ServerClientThreadManager.cleanClientThreadsGarbage();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(
        runnable,
        CommonConfig.SERVER_CRONTAB_CHECK_CLIENT_SOCKET_INTERVAL,
        CommonConfig.SERVER_CRONTAB_CHECK_CLIENT_SOCKET_INTERVAL,
        TimeUnit.MILLISECONDS);
  }
}
