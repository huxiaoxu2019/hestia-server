package com.ihuxu.hestia.server.library.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ClientThreadManager {

    private static HashMap<String, ClientThread> clientServerThreadHashMap = new HashMap<String, ClientThread>();

    public static ClientThread getClientThread(String key) throws Exception {
        if(ClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
            return ClientThreadManager.clientServerThreadHashMap.get(key);
        } else {
            throw new Exception("There is not the ClientThread in the clientServerThreadHashMap variable.");
        }
    }

    public static ClientThread setClientThread(String key, ClientThread clientThread) {
        return ClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
    }

    public static boolean addClientThread(String key, ClientThread clientThread) {
        if(!ClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
            ClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
            return true;
        }
        return false;
    }

    public static void removeClientThread(String key) {
        ClientThreadManager.clientServerThreadHashMap.remove(key);
    }

    public static int getClientThreadsCount() {
        return ClientThreadManager.clientServerThreadHashMap.size();
    }

    public static void cleanClientThreadsGarbage() {
        Iterator<Map.Entry<String, ClientThread>> iterator = ClientThreadManager.clientServerThreadHashMap.entrySet().iterator();
        Vector<String> needRemoveClientKeys = new Vector<String>();
        while (iterator.hasNext()) {
            Map.Entry<String, ClientThread> entry = iterator.next();
            String key = entry.getKey();
            try {
                if (ClientThreadManager.getClientThread(key).checkValid() == false) {
                    System.out.println("trash client thread key:" + key);
                    ClientThreadManager.getClientThread(key).close();
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
                ClientThreadManager.removeClientThread(vectorIterator.next());
            }
        }
    }
}
