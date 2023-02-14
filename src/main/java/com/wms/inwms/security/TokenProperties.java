package com.wms.inwms.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("security.jwt")
public class TokenProperties {
    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    private String loginPath = "/login";

    public String getLoginPath() {
        return this.loginPath;
    }

    private String header = "Authorization";

    public String getHeader() {
        return this.header;
    }

    private String prefix = "Bearer ";

    public String getPrefix() {
        return this.prefix;
    }

    private int expiration = 86400;

    public int getExpiration() {
        return this.expiration;
    }

    private String secret = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    public String getSecret() {
        return this.secret;
    }
}
