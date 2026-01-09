package com.fitrah.dento.gatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter, Ordered {

    /**
     * The filter method is a Spring Cloud Gateway global filter that logs each incoming request
     *  method and URI
     *
     * The method returns chain.filter(exchange) which is a Mono<Void> that continues the reactive filter chain.
     *  This is non-blocking: the filter should avoid blocking operations because it runs in a reactive/non-blocking context.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();
        log.info("Incoming request: {} {}", request.getMethod(), request.getURI());

        return chain.filter(exchange);
    }

    /**
     *  getOrder() returns -1, so this filter has higher precedence (runs earlier)
     *     than filters with larger order values (lower numeric order = higher priority).
     */
    @Override
    public int getOrder() {
        return -1;
    }
}