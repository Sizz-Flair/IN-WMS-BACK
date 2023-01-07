package com.wms.inwms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.user.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url, "GET"));
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        UserCredentials user = new UserCredentials();
        user.setUsername("akfur2");
        user.setPassword("a1234");
        UsernamePasswordAuthenticationToken token = createAuthenticationToken(user);

        return getAuthenticationManager().authenticate(token);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(UserCredentials user) {
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}