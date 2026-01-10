package com.fitrah.dento.gatewayserver.config;

import com.fitrah.dento.dento_security_util.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public JwtUtil jwtUtil(@Value("${jwt.secret}") String secret) {
        return new JwtUtil(secret);
    }
}