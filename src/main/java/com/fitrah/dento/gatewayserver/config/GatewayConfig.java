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
            .route("auth-service", r -> r.path("/auth/**").uri("lb://auth-service")) // use load balancing from Eureka
            .route("patient-service", r -> r.path("/patients/**").uri("lb://patient-service"))
            .route("dentist-service", r -> r.path("/dentists/**").uri("lb://dentist-service"))
            .route("appointment-service", r -> r.path("/appointments/**").uri("lb://appointment-service"))
            .route("treatment-service", r -> r.path("/treatments/**").uri("lb://treatment-service"))
            .route("billing-service", r -> r.path("/billing/**").uri("lb://billing-service"))
            .route("notification-service", r -> r.path("/notifications/**").uri("lb://notification-service"))
            .route("reporting-service", r -> r.path("/reports/**").uri("lb://reporting-service"))
            .build();
    }
}