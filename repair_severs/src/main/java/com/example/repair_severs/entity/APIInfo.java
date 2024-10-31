package com.example.repair_severs.entity;

import lombok.Data;

@Data
public class APIInfo {
    private String apiName;
    private String provider;
    private String apiType;
    private String authenticationMethod;
    private String credentials;
    private String host;
    private String documentationLink;
    private String accessFrequency;
    private String connectionStatus;

    // Getter 和 Setter 方法
}
