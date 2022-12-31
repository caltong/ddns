package com.caltong.ddns;

import com.caltong.ddns.dns.DnsProvider;
import com.caltong.ddns.ip.IpChecker;
import com.caltong.ddns.properties.DdnsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class DdnsService {

    private final Logger log = LoggerFactory.getLogger(DdnsService.class);


    private DnsProvider dnsProvider;

    private IpChecker ipChecker;

    private DdnsProperties ddnsProperties;

    private ScheduledExecutorService scheduledExecutorService;

    public DdnsService(DnsProvider dnsProvider, IpChecker ipChecker, DdnsProperties ddnsProperties, ScheduledExecutorService scheduledExecutorService) {
        this.dnsProvider = dnsProvider;
        this.ipChecker = ipChecker;
        this.ddnsProperties = ddnsProperties;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @PostConstruct
    public void createScheduledTask() {
        Runnable task = () -> {
            try {
                String currentPublicIp = ipChecker.getCurrentPublicIp();
                String currentFromDnsProvider = dnsProvider.getIp();
                if (currentFromDnsProvider != null && !currentFromDnsProvider.equals(currentPublicIp)) {
                    dnsProvider.setIp(currentPublicIp);
                }
            } catch (Exception e) {
                log.error("Got exception during the task: {}", e.toString());
            }
        };
        log.info("Task interval: {}, task interval unit: {}", ddnsProperties.getInterval(), ddnsProperties.getIntervalUnit());
        scheduledExecutorService.scheduleAtFixedRate(task, 0, ddnsProperties.getInterval(), ddnsProperties.getIntervalUnit());
    }
}
