package com.itmo.lab2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MasterService {

    @Autowired
    SlaveService slaveService;

    public String getByKey(String key) {
        ArrayList<String> tempList = new ArrayList<>(slaveService.getSlavesAddresses());
        while (!tempList.isEmpty()) {
            int index = slaveService.chooseSlaveFromList(tempList);
            String ip = tempList.get(index);
            tempList.remove(index);
            String result = slaveService.getByKey(ip, key);
            if (result != null) return result;
        }
        return "";
    }

    public ArrayList<String> getAll() {
        ArrayList<String> resultList = new ArrayList<>();
        for (String ip : slaveService.getSlavesAddresses()) {
            resultList.addAll(slaveService.getAll(ip));
        }
        return resultList;
    }

    public String put(String key, String value) {

        if (getByKey(key) == null) {
            String ip = slaveService.chooseSlave();
            return slaveService.put(ip, key, value);
        } else {
            ArrayList<String> tempList = new ArrayList<>(slaveService.getSlavesAddresses());
            while (!tempList.isEmpty()) {
                int index = slaveService.chooseSlaveFromList(tempList);
                String ip = tempList.get(index);
                tempList.remove(index);
                String result = slaveService.getByKey(ip, key);
                if (result != null) return slaveService.put(ip, key, value);

            }
        }
        return "Failed";
    }
}
