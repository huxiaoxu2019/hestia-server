package com.ihuxu.hestia.server.library.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ihuxu.hestia.server.config.CommonConfig;
import com.ihuxu.hestia.server.library.crontab.CrontabHandler;

public class ServerHandler {
    private ServerSocket ss;

    public void start() {
        try {
            CrontabHandler.checkClientSocket();
            ss = new ServerSocket(CommonConfig.SERVER_SOCKET_PORT);
            while (true) {
                Socket s = ss.accept();
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
        }
    }
}
