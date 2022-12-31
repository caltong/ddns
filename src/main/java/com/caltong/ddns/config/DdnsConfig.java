package com.caltong.ddns.config;

import com.caltong.ddns.properties.CloudflareProperties;
import com.caltong.ddns.properties.DdnsProperties;
import com.caltong.ddns.dns.cloudflare.CloudflareDns;
import com.caltong.ddns.dns.DnsProvider;
import com.caltong.ddns.dns.DnsProviderEnum;
import com.caltong.ddns.ip.IpChecker;
import com.caltong.ddns.ip.NetCnIpChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class DdnsConfig {

    private DdnsProperties ddnsProperties;

    private CloudflareProperties cloudflareProperties;

    public DdnsConfig(DdnsProperties ddnsProperties, CloudflareProperties cloudflareProperties) {
        this.ddnsProperties = ddnsProperties;
        this.cloudflareProperties = cloudflareProperties;
    }

    @Bean
    public DnsProvider createDnsProvider() {
        if (ddnsProperties.getDnsProvider() == DnsProviderEnum.CLOUDFLARE) {
            return new CloudflareDns(
                    ddnsProperties.getDomain(),
                    cloudflareProperties.getToken(),
                    cloudflareProperties.getZoneId()
            );
        } else {
            //set default to cloudflare dns
            return new CloudflareDns(
                    ddnsProperties.getDomain(),
                    cloudflareProperties.getToken(),
                    cloudflareProperties.getZoneId());
        }
    }

    @Bean
    public IpChecker createIpChecker() {
        return new NetCnIpChecker();
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }
}
