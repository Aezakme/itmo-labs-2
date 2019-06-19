package com.itmo.lab2.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HttpService {

    private static final String KEY_PARAM = "key=";
    private static final String HTTP = "http://";
    private static final String VALUE_PARAM = "value=";

    String getLocalDataByKeyRequest(String ip, String key) {
        String url = HTTP + ip + "/dataLocal?" + KEY_PARAM + key;
        return executeRequest(url, "GET");
    }

    String getAllLocalDataRequest(String ip) {
        String url = HTTP + ip + "/dataLocalAll";
        return executeRequest(url, "GET");
    }

    String putRequest(String ip, String key, String value) {
        String url = HTTP + ip + "/data?" + KEY_PARAM + key + "&" + VALUE_PARAM + value;
        return executeRequest(url, "PUT");
    }

    private String executeRequest(String url, String method) {
        try {

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod(method);


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Integer getSizeRequest(String ip) {
        String url = HTTP + ip + "/dataSize";
        String get = executeRequest(url, "GET");
        if (get != null) return Integer.parseInt(get);
        return null;
    }
}
