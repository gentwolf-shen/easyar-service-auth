package com.easyar.service.app.crs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrsResultInfo {
    @JsonProperty("target")
    private CrsTarget target;
    private String message;

    public CrsResultInfo() {
    }

    public CrsTarget getTarget() {
        return target;
    }

    public void setTarget(CrsTarget target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
