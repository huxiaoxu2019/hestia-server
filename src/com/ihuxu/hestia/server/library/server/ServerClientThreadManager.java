package com.ihuxu.hestia.server.library.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ServerClientThreadManager {

    private static HashMap<String, ServerClientThread> clientServerThreadHashMap = new HashMap<String, ServerClientThread>();

    public static ServerClientThread getClientThread(String key) throws Exception {
        if(ServerClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
            return ServerClientThreadManager.clientServerThreadHashMap.get(key);
        } else {
            throw new Exception("There is not the ClientThread in the clientServerThreadHashMap variable.");
        }
    }

    public static ServerClientThread setClientThread(String key, ServerClientThread clientThread) {
        return ServerClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
    }

    public static boolean addClientThread(String key, ServerClientThread clientThread) {
        if(!ServerClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
            ServerClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
            return true;
        }
        return false;
    }

    public static void removeClientThread(String key) {
        ServerClientThreadManager.clientServerThreadHashMap.remove(key);
    }

    public static int getClientThreadsCount() {
        return ServerClientThreadManager.clientServerThreadHashMap.size();
    }

    public static boolean changeClientKey(String originalKey, String newKey) {
        try {
            // If there is already one the client with the same key
            // then return false to disconnect with the client.
            ServerClientThreadManager.getClientThread(originalKey).setClientKey(originalKey);
            boolean result = ServerClientThreadManager.addClientThread(newKey, ServerClientThreadManager.getClientThread(originalKey));
            if (result == false) {
                return false;
            }
            ServerClientThreadManager.removeClientThread(originalKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void cleanClientThreadsGarbage() {
        Iterator<Map.Entry<String, ServerClientThread>> iterator = ServerClientThreadManager.clientServerThreadHashMap.entrySet().iterator();
        Vector<String> needRemoveClientKeys = new Vector<String>();
        while (iterator.hasNext()) {
            Map.Entry<String, ServerClientThread> entry = iterator.next();
            String key = entry.getKey();
            try {
                if (ServerClientThreadManager.getClientThread(key).checkValid() == false) {
                    System.out.println("trash client thread key:" + key);
                    ServerClientThreadManager.getClientThread(key).close();
                    needRemoveClientKeys.add(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // DO MUST NOT to remove item from the HashMap out of the while block above,
        // Because if do, you would get the exception of modifying the HashMap concurrently.
        if (needRemoveClientKeys.size() > 0) {
            Iterator<String> vectorIterator = needRemoveClientKeys.iterator();
            while (vectorIterator.hasNext()) {
                ServerClientThreadManager.removeClientThread(vectorIterator.next());
            }
        }
    }
}
