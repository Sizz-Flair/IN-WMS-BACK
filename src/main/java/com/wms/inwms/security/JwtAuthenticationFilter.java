package com.wms.inwms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.domain.user.UserCredentials;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url, "POST"));
        this.objectMapper = new ObjectMapper();
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserCredentials user = getUserCredentials(request);
        UsernamePasswordAuthenticationToken token = createAuthenticationToken(user);
        return getAuthenticationManager().authenticate(token);
    }

    private UserCredentials getUserCredentials(HttpServletRequest request) throws IOException {
        return this.objectMapper.readValue(request.getInputStream(), UserCredentials.class);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(UserCredentials user) {
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList());
    }
}
