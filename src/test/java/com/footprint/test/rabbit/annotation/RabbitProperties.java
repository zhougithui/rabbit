package com.footprint.test.rabbit.annotation;

public class RabbitProperties {
    private String addresses = "192.168.106.130:5672,192.168.106.132:5672";
    private String username = "zh";
    private String password = "zh";
    private String virtualHost = "/";

    public String getAddresses() {
        return addresses;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }
}
