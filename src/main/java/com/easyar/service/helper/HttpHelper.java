package com.easyar.service.helper;

import okhttp3.*;

import java.io.IOException;

public class HttpHelper {
    private static OkHttpClient httpClient = new OkHttpClient();

    public byte[] postJson(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().bytes();
        }
    }

    public byte[] postJson(String url, String json, String authorization) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("authorization", authorization)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().bytes();
        }
    }
}
