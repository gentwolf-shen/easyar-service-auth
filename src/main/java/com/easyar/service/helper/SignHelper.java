package com.easyar.service.helper;

import java.security.MessageDigest;
import java.util.SortedMap;

public class SignHelper {

    public String getSign(SortedMap<String, String> params, String apiSecret) {
        StringBuilder builder = new StringBuilder();
        params.forEach((key, value) -> builder.append(key + value));

        return this.sha256(builder.toString() + apiSecret);
    }

    public String sha256(String str) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("sha-256");
            return this.byteToHexString(hasher.digest(str.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public String byteToHexString(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte b: data) {
            String hex = Integer.toHexString(b & 0XFF);
            builder.append(hex.length() == 1 ? "0" + hex : hex);
        }
        return builder.toString();
    }
}
