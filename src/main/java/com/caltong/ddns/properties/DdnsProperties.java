package com.caltong.ddns.properties;


import com.caltong.ddns.dns.DnsProviderEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "ddns")
public class DdnsProperties {

    private DnsProviderEnum dnsProvider;

    private String domain;

    private String token;

    private Long interval;

    private TimeUnit intervalUnit;

    public DnsProviderEnum getDnsProvider() {
        return dnsProvider;
    }

    public DdnsProperties setDnsProvider(DnsProviderEnum dnsProvider) {
        this.dnsProvider = dnsProvider;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public DdnsProperties setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getToken() {
        return token;
    }

    public DdnsProperties setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getInterval() {
        return interval;
    }

    public DdnsProperties setInterval(Long interval) {
        this.interval = interval;
        return this;
    }

    public TimeUnit getIntervalUnit() {
        return intervalUnit;
    }

    public DdnsProperties setIntervalUnit(TimeUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
        return this;
    }
}
