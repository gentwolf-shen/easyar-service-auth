package com.easyar.service.app.pose;

import com.easyar.service.app.base.Base;

import java.io.IOException;


public class Pose extends Base {
    public Pose(String appId, String appUrl) {
        super(appId, appUrl);
    }

    public Pose(String apiKey, String apiSecret, String appId, String appUrl) {
        super(apiKey, apiSecret, appId, appUrl);
    }

    public PoseResult searchByToken(String token, String image) throws IOException {
        return this.searchByToken(token, image, PoseResult.class);
    }

    public PoseResult searchBySignature(String image) throws IOException {
        return this.searchBySignature(image, PoseResult.class);
    }
}
