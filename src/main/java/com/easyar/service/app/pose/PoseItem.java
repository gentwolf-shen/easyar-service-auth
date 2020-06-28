package com.easyar.service.app.pose;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoseItem {
    private String requestTime;
    @JsonProperty("class")
    private HashMap<String, Object> items;

    public PoseItem() {
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public HashMap<String, Object> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Object> items) {
        this.items = items;
    }
}
