package com.itmo.lab2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ConnectionService {

    @Autowired
    HttpService service;

    private ArrayList<String> peersAddresses = new ArrayList<>();

    @PostConstruct
    public void setPeersAddresses() {
        peersAddresses.add("127.0.0.1:8888");
        peersAddresses.add("127.0.0.1:8889");
    }

    int choosePeerFromList(ArrayList<String> peersAddresses) {
        return new Random().nextInt(peersAddresses.size());  //очень математичный расчет пира (никому не говорить)
    }


    ArrayList<String> getPeersAddresses() {
        return peersAddresses;
    }

    String getLocalDataByKey(String ip, String key) {
        return service.getLocalDataByKeyRequest(ip, key);
    }

    List<String> getAll(String ip) {
        ArrayList<String> result = new ArrayList<>();
        String request = service.getAllLocalDataRequest(ip);
        if (request != null && request.length() > 3)
            Collections.addAll(result, request.substring(2, request.length() - 2).split("\",\""));
        return result;
    }

    String put(String ip, String key, String value) {
        return service.putRequest(ip, key, value);
    }

    Integer getSize(String ip) {
        Integer sizeRequest = service.getSizeRequest(ip);
        if (sizeRequest == null) {
            peersAddresses.remove(ip);
        }
        return sizeRequest;
    }
}
