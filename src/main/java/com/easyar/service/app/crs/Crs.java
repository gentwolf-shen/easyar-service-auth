package com.easyar.service.app.crs;

import com.easyar.service.app.base.Base;

import java.io.IOException;

public class Crs extends Base {
    public Crs(String appId, String appUrl) {
        super(appId, appUrl);
    }

    public Crs(String apiKey, String apiSecret, String appId, String appUrl) {
        super(apiKey, apiSecret, appId, appUrl);
    }

    /**
     * Token方式搜索图片
     *
     * @param token
     * @param image
     * @return
     * @throws IOException
     */
    public CrsResult searchByToken(String token, String image) throws IOException {
        return this.searchByToken(token, image, CrsResult.class);
    }

    /**
     * 签名方式搜索图片
     *
     * @param image
     * @return
     * @throws IOException
     */
    public CrsResult searchBySignature(String image) throws IOException {
        return this.searchBySignature(image, CrsResult.class);
    }
}
