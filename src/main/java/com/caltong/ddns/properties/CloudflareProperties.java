package com.caltong.ddns.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "ddns.cloudflare")
public class CloudflareProperties {

    private String zoneId;

    private String token;

    public String getZoneId() {
        return zoneId;
    }

    public CloudflareProperties setZoneId(String zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public CloudflareProperties setToken(String token) {
        this.token = token;
        return this;
    }
}
