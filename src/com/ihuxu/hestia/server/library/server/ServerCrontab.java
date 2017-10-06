package com.ihuxu.hestia.server.library.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ihuxu.hestia.server.config.Common;

public class ServerCrontab {

    private static boolean clientSockedChecked = false;

    public ServerCrontab() {}

    public static void checkClientSocket() {
        if (ServerCrontab.isClientSocketChecked()) {
            System.out.println("has already checked.");
            return;
        } else {
            ServerCrontab.setClientSockedChecked(true);
        }
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("ServerCrontab -> the client thread count is " + ClientThreadManager.getClientThreadsCount());
                    ClientThreadManager.cleanClientThreadsGarbage();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, Common.SERVER_SOCKET_CRONTAB_INTERVAL, Common.SERVER_SOCKET_CRONTAB_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public static boolean isClientSocketChecked() {
        return clientSockedChecked;
    }

    public static void setClientSockedChecked(boolean clientSockedChecked) {
        ServerCrontab.clientSockedChecked = clientSockedChecked;
    }

}
