package com.easyar.service.app.pose;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PoseResult {
    private int statusCode;
    private String date;
    private long timestamp;
    @JsonProperty("msg")
    private String message;
    private HashMap<String, PoseItem> result;


    public PoseResult() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, PoseItem> getResult() {
        return result;
    }

    public void setResult(HashMap<String, PoseItem> result) {
        this.result = result;
    }
}
