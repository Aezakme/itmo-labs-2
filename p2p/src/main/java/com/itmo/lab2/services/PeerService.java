package com.itmo.lab2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PeerService {

    @Autowired
    ConnectionService connectionService;

    private Map<String, String> storage = new HashMap<>();

    public String getByKey(String key) {
        if (getLocalDataByKey(key) != null) {
            return getLocalDataByKey(key);
        }

        ArrayList<String> tempList = new ArrayList<>(connectionService.getPeersAddresses());
        while (!tempList.isEmpty()) {
            int index = connectionService.choosePeerFromList(tempList);
            String ip = tempList.get(index);
            tempList.remove(index);
            String result = connectionService.getLocalDataByKey(ip, key);
            if (result != null) return result;
        }
        return "";
    }

    public ArrayList<String> getAll() {
        ArrayList<String> resultList = new ArrayList<>();
        for (String ip : connectionService.getPeersAddresses()) {
            resultList.addAll(connectionService.getAll(ip));
        }
        return resultList;
    }

    public String put(String key, String value) {

        ArrayList<String> tempList = new ArrayList<>(connectionService.getPeersAddresses());
        while (!tempList.isEmpty()) {

            int index = connectionService.choosePeerFromList(tempList);
            String ip = tempList.get(index);
            tempList.remove(index);

            Integer size = connectionService.getSize(ip);

            if (size != null && size < storage.size())
                return connectionService.put(ip, key, value);

        }

        return storage.put(key, value);
    }

    public int getDataSize() {
        return storage.size();
    }

    public String getLocalDataByKey(String key) {
        return storage.get(key);
    }

    public ArrayList<String> getAllLocalData() {
        return new ArrayList<>(storage.values());
    }
}
