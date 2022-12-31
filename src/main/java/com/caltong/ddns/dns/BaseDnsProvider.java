package com.caltong.ddns.dns;

public abstract class BaseDnsProvider implements DnsProvider {

    protected String domain;

    protected String ip;

    public BaseDnsProvider(String domain, String ip) {
        this.domain = domain;
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }
}
