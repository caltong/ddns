package com.caltong.ddns;

import com.caltong.ddns.properties.DdnsProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DdnsPropertiesTest {

    private final Logger log = LoggerFactory.getLogger(DdnsPropertiesTest.class);

    @Autowired
    DdnsProperties ddnsProperties;

    @Test
    public void testDdnsProperties() {
        log.info(ddnsProperties.getDnsProvider().toString());
        log.info(ddnsProperties.getDomain());
        log.info(ddnsProperties.getIntervalUnit().toString());
    }

}