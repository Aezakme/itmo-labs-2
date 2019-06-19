package com.itmo.lab2.services;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SlaveService {

    private Map<String, String> storage = new HashMap<>();

    public void put(String key, String value) {
        storage.put(key, value);
    }

    public String getByKey(String key) {
        return storage.get(key);
    }

    public ArrayList<String> getAll() {
        return new ArrayList<>(storage.values());
    }
}