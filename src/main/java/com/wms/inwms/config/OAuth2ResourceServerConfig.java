package com.wms.inwms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuth2ResourceServerConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(request -> request.anyRequest().authenticated());
//        //http.oauth2ResourceServer(OAuth2ResourceServerConfig -> OAuth2ResourceServerConfig.jwt());
//        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//        return http.build();
//    }
}
