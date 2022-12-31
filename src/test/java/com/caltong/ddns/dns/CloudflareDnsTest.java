package com.caltong.ddns.dns;

import com.caltong.ddns.dns.cloudflare.CloudflareDns;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudflareDnsTest {

    private final Logger log = LoggerFactory.getLogger(CloudflareDnsTest.class);

    @Autowired
    CloudflareDns cloudflareDns;

    @Test
    void getIp() {
        String ip = cloudflareDns.getIp();
        log.info(ip);
    }

    @Test
    void setIp() {
        String ip = cloudflareDns.getIp();
        cloudflareDns.setIp(ip);
    }
}