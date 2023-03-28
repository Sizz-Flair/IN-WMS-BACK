package com.wms.inwms.security;


import com.wms.inwms.domain.user.User;
import com.wms.inwms.security.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        //User user = this.customUserDetailsService.loadUserByUsername(username);
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

        if (!this.bCryptPasswordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("패스워드를 확인해주세요");

//        Map<String, Object> detailsInfo = new HashMap<>();
//        detailsInfo.put("role", user.getUserCredentials().getRole());
//        detailsInfo.put("agent", user.getAgent());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
