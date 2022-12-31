package com.caltong.ddns.dns.cloudflare;

import com.caltong.ddns.dns.BaseDnsProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CloudflareDns extends BaseDnsProvider {
    private final Logger log = LoggerFactory.getLogger(CloudflareDns.class);
    private String zoneId;

    private String id;

    private String apiToken;

    private String getIpApi;

    private String setIpApi;

    private final String baseUrl = "https://api.cloudflare.com/client/v4/zones/";

    public CloudflareDns(String domain, String apiToken, String zoneId) {
        super(domain, "default ip");
        this.apiToken = apiToken;
        this.zoneId = zoneId;
        this.getIpApi = baseUrl + zoneId + "/dns_records?type=A&name=" + this.domain;
        getIp();
        this.setIpApi = baseUrl + zoneId + "/dns_records/" + id;
    }

    @Override
    public String getIp() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getIpApi))
                .header("Authorization", "Bearer " + this.apiToken)
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String currentIp = null;
        if (response.statusCode() == 200) {
            JsonNode result = jsonNode.get("result").get(0);
            currentIp = result.get("content").asText();
            String id = result.get("id").asText();

            this.id = id;
            this.ip = currentIp;
            log.info("Current ip from dns provider: {}, id: {}", currentIp, id);
        } else {
            log.error("Get current ip from dns provider fail: {}", response.statusCode());
        }
        return currentIp;
    }

    @Override
    public void setIp(String ip) {
        HttpClient client = HttpClient.newHttpClient();

        CloudflareSetIpRequestBody cloudflareSetIpRequestBody = new CloudflareSetIpRequestBody(
                "A",
                domain,
                ip,
                0L,//0 stands for auto ttl
                false,//ddns should not be proxied
                "Updated by ddns@caltong",
                null
        );
        String requestBody;
        try {
            requestBody = new ObjectMapper().writeValueAsString(cloudflareSetIpRequestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(setIpApi))
                .header("Authorization", "Bearer " + this.apiToken)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();

        if (statusCode != 200) {
            log.error("Set ip fail");
        }

        log.info("Set ip to: {}, status code: {}", ip, statusCode);
    }
}
