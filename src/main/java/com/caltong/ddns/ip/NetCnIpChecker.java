package com.caltong.ddns.ip;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NetCnIpChecker implements IpChecker {

    private final Logger log = LoggerFactory.getLogger(NetCnIpChecker.class);


    private String api = "http://www.net.cn/static/customercare/yourip.asp";

    public NetCnIpChecker() {

    }

    @Override
    public String getCurrentPublicIp() {
        Document document = null;
        try {
            document = Jsoup.connect(api).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String currentIp = document.getElementsByTag("center").get(0).getElementsByTag("h2").get(0).text();

        log.info("Current ip from ip checker: {}", currentIp);

        return currentIp;
    }
}
