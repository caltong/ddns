package com.caltong.ddns.dns.cloudflare;

import java.util.List;

public class CloudflareSetIpRequestBody {
    private String type;

    private String name;

    private String content;

    private Long ttl;

    private Boolean proxied;

    private String comment;

    private List<String> tags;

    public CloudflareSetIpRequestBody() {
    }

    public CloudflareSetIpRequestBody(String type, String name, String content, Long ttl, Boolean proxied, String comment, List<String> tags) {
        this.type = type;
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.proxied = proxied;
        this.comment = comment;
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public CloudflareSetIpRequestBody setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public CloudflareSetIpRequestBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CloudflareSetIpRequestBody setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getTtl() {
        return ttl;
    }

    public CloudflareSetIpRequestBody setTtl(Long ttl) {
        this.ttl = ttl;
        return this;
    }

    public Boolean getProxied() {
        return proxied;
    }

    public CloudflareSetIpRequestBody setProxied(Boolean proxied) {
        this.proxied = proxied;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CloudflareSetIpRequestBody setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public CloudflareSetIpRequestBody setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
