package com.wms.inwms.security;


import com.wms.inwms.security.TokenProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final Key key;

    private final TokenProperties tokenProperties;

    public JwtAuthenticationSuccessHandler(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
        byte[] keyBytes = Decoders.BASE64.decode(tokenProperties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        response.addHeader("Authorization", createToken(authentication));
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.addHeader("Authorization", createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        long now = System.currentTimeMillis();
        List<String> authorities = (List<String>)authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + (this.tokenProperties.getExpiration() * 1000)))
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();
    }
}
