package com.fitrah.dento.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator dentoGatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route("auth-service", r -> r.path("/auth/**").uri("http://localhost:8081"))
            .route("patient-service", r -> r.path("/patients/**").uri("http://localhost:8082"))
            .route("dentist-service", r -> r.path("/dentists/**").uri("http://localhost:8083"))
            .route("appointment-service", r -> r.path("/appointments/**").uri("http://localhost:8084"))
            .route("treatment-service", r -> r.path("/treatments/**").uri("http://localhost:8085"))
            .route("billing-service", r -> r.path("/billing/**").uri("http://localhost:8086"))
            .route("notification-service", r -> r.path("/notifications/**").uri("http://localhost:8087"))
            .route("reporting-service", r -> r.path("/reports/**").uri("http://localhost:8088"))
            .build();
    }
}