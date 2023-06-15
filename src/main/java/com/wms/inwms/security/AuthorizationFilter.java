package com.wms.inwms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private final TokenProperties tokenProperties;

    public AuthorizationFilter(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(this.tokenProperties.getHeader());
        if (headerIsValid(header))
            try {
                Claims claims = getClaims(getToken(header));
                Optional.ofNullable(claims.getSubject()).ifPresent(username -> setUserContext(claims, username));
            } catch (Exception exception) {}
        filterChain.doFilter(request, response);
    }

    private boolean headerIsValid(String header) {
        return (header != null && header.startsWith(this.tokenProperties.getPrefix()));
    }

    private String getToken(String header) {
        return header.replace(this.tokenProperties.getPrefix(), "");
    }

    private Claims getClaims(String token) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(this.tokenProperties.getSecret());
            Claims Claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(keyBytes)).build().parseClaimsJws(token).getBody();
            return Claims;
        } catch (Exception e) {
            throw e;
        }
    }

    private void setUserContext(Claims claims, String username) {
        List<GrantedAuthority> userData = new ArrayList<>();
        userData.add(new SimpleGrantedAuthority(String.valueOf(claims.get("agent"))));

        User userDetails = new User(username, "", userData);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, getGrantedAuthorities(claims));
        auth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private List<SimpleGrantedAuthority> getGrantedAuthorities(Claims claims) {
        return ((List<String>) claims.get("authorities")).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
