package com.itmo.lab2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class SlaveService {

    @Autowired
    HttpService service;

    private ArrayList<String> slavesAddresses = new ArrayList<>();

    @PostConstruct
    public void setSlavesAddresses() {
        slavesAddresses.add("127.0.0.1:8888");
        slavesAddresses.add("127.0.0.1:8889");
    }

    String chooseSlave() {
        int index = chooseSlaveFromList(slavesAddresses);
        return slavesAddresses.get(index);
    }

    int chooseSlaveFromList(ArrayList<String> slavesAddresses) {
        return new Random().nextInt(slavesAddresses.size());  //очень математичный расчет помошника (никому не говорить)
    }


    ArrayList<String> getSlavesAddresses() {
        return slavesAddresses;
    }

    String getByKey(String ip, String key) {
        return service.getByKeyRequest(ip, key);
    }

    List<String> getAll(String ip) {
        ArrayList<String> result = new ArrayList<>();
        String request = service.getRequest(ip);
        if (request != null && request.length() > 3)
            Collections.addAll(result, request.substring(2, request.length() - 2).split("\",\""));
        return result;

    }

    String put(String ip, String key, String value) {
        return service.putRequest(ip, key, value);
    }
}
