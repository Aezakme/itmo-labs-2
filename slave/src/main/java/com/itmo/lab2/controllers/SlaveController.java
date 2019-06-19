package com.itmo.lab2.controllers;


import com.itmo.lab2.services.SlaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SlaveController {

    private final SlaveService service;

    @Autowired
    public SlaveController(SlaveService service) {
        this.service = service;
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/data")
    public String getDataByKey(@RequestParam("key") String key) {
        return service.getByKey(key);
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/dataAll")
    public ArrayList<String> getAllData() {
        return service.getAll();
    }

    @ApiOperation(value = "----", notes = "---")
    @PutMapping(value = "/data")
    public String putData(@RequestParam("key") String key, @RequestParam("value") String value) {
        service.put(key, value);
        return value;
    }

}
