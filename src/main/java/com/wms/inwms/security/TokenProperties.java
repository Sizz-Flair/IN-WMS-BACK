package com.wms.inwms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;

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

    private int expiration = 84600;

    public int getExpiration() {
        return this.expiration;
    }

    private String secret = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());

    public String getSecret() {
        return this.secret;
    }

    public Claims getTokenInfo(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(keyBytes)).build().parseClaimsJws(token.replace("Bearer","")).getBody();
    }
}
