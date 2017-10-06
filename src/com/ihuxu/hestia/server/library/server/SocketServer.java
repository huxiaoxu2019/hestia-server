package com.ihuxu.hestia.server.library.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ihuxu.hestia.server.config.Common;

public class SocketServer {
    private ServerSocket ss;

    public void start() {
        try {
        		ServerCrontab.checkClientSocket();
            ss = new ServerSocket(Common.SERVER_PORT);
            while (true) {
                Socket s = ss.accept();
                try {
                    ClientThread clientThread = new ClientThread(s);
                    if(ClientThreadManager.addClientThread(clientThread.getClientKey(), clientThread)) {
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
