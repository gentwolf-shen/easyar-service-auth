package com.easyar.service.auth.token;

import com.easyar.service.helper.HttpHelper;
import com.easyar.service.helper.SignHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class Token {

    private final static String tokenServer = "https://uac.easyar.com/token/v2";

    public Token() {
    }

    public TokenResult getToken(String apiKey, String apiSecret, int expires) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        SortedMap<String, String> params = new TreeMap<>();
        params.put("apiKey", apiKey);
        params.put("expires", Integer.toString(expires));
        params.put("timestamp", Long.toString(System.currentTimeMillis()));
//        params.put("acl", mapper.writeValueAsString(acl));
        params.put("signature", new SignHelper().getSign(params, apiSecret));

        String json = mapper.writeValueAsString(params);

        byte[] body = new HttpHelper().postJson(tokenServer, json);
        TokenResult tokenResult = mapper.readValue(body, TokenResult.class);

        return tokenResult;
    }
}
