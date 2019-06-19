package com.itmo.lab2.controllers;


import com.itmo.lab2.services.PeerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PeerController {

    private final PeerService peerService;

    @Autowired
    public PeerController(PeerService peerService) {
        this.peerService = peerService;
    }

    @GetMapping(value = "/dataSize")
    public int getDataSize() {
        return peerService.getDataSize();
    }

    @GetMapping(value = "/dataLocal")
    public String getLocalDataByKey(@RequestParam("key") String key) {
        return peerService.getLocalDataByKey(key);
    }

    @GetMapping(value = "/dataLocalAll")
    public ArrayList<String> getAllLocalData() {
        return peerService.getAllLocalData();
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/data")
    public String getDataByKey(@RequestParam("key") String key) {
        return peerService.getByKey(key);
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/dataAll")
    public ArrayList<String> getAllData() {
        return peerService.getAll();
    }

    @ApiOperation(value = "----", notes = "---")
    @PutMapping(value = "/data")
    public String putData(@RequestParam("key") String key, @RequestParam("value") String value) {
        return peerService.put(key, value);
    }

}
