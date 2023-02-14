package com.wms.inwms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(com.wms.inwms.security.JwtAuthenticationFailureHandler.class);

    private ObjectMapper objectMapper = new ObjectMapper();
}