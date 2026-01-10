package com.fitrah.dento.gatewayserver.filter;

import com.fitrah.dento.dento_security_util.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("inside JwtAuthenticationFilter.filter");
        var path = exchange.getRequest().getURI().getPath();

        // do NOT require a JWT to access the authentication service
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        var authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }


        var token = authHeader.substring(7); // to exclude 'Bearer' word from the token

        Claims claims = jwtUtil.extractClaims(token);

        String username = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        // TEMPORARY: log to verify
        log.info("Authenticated user: {}", username);
        log.info("Roles: {}", roles);

        try {
            jwtUtil.extractUsername(token);
        } catch (Exception _) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}