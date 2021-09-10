package com.ihuxu.hestia.server.library.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.crontab.CrontabHandler;

public class ServerHandler {
    private static ServerSocket ss;
    private static boolean didRun = false;

    public static void start() {
        try {
            if (ServerHandler.didRun) {
                throw new Exception("the ServerHandler.start() has been called before.");
            } else {
                ServerHandler.didRun = true;
            }
            CrontabHandler.checkClientSocket();
            ServerHandler.ss = new ServerSocket(CommonConfig.SERVER_SOCKET_PORT);
            while (true) {
                Socket s = ServerHandler.ss.accept();
                try {
                    ServerClientThread clientThread = new ServerClientThread(s);
                    if(ServerClientThreadManager.addClientThread(clientThread.getClientKey(), clientThread)) {
                        clientThread.start();
                    } else {
                        clientThread.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
