package com.easyar.service.app.base;

import com.easyar.service.helper.HttpHelper;
import com.easyar.service.helper.SignHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Base {
    private String apiKey;
    private String apiSecret;
    private String appId;
    private String appUrl;

    public Base(String appId, String appUrl) {
        this.appId = appId;
        this.appUrl = appUrl;
    }

    public Base(String apiKey, String apiSecret, String appId, String appUrl) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.appId = appId;
        this.appUrl = appUrl;
    }

    public <T> T searchByToken(String token, String image, Class<T> target) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        params.put("image", image);
        params.put("appId", this.appId);

        byte[] data = new HttpHelper().postJson(appUrl, mapper.writeValueAsString(params), token);

        return mapper.readValue(data, target);
    }

    public <T> T searchBySignature(String image, Class<T> target) throws IOException {
        SortedMap<String, String> params = new TreeMap<>();
        params.put("image", image);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("appId", this.appId);
        params.put("apiKey", this.apiKey);
        params.put("signature", new SignHelper().getSign(params, this.apiSecret));

        ObjectMapper mapper = new ObjectMapper();

        byte[] data = new HttpHelper().postJson(appUrl, mapper.writeValueAsString(params));
        return mapper.readValue(data, target);
    }
}
