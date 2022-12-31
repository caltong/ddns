package com.caltong.ddns.ip;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class NetCnIpCheckerTest {

    private final Logger log = LoggerFactory.getLogger(NetCnIpCheckerTest.class);

    private NetCnIpChecker netCnIpChecker = new NetCnIpChecker();

    @Test
    void getCurrentPublicIp() {
        String currentPublicIp = netCnIpChecker.getCurrentPublicIp();
        log.info(currentPublicIp);

    }
}