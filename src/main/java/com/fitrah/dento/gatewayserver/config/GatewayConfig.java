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
            .route("auth-service", r -> r.path("/auth/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://AUTH-SERVICE")) // use load balancing from Eureka
            .route("patient-service", r -> r.path("/patients/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://PATIENT-SERVICE"))
            .route("dentist-service", r -> r.path("/dentists/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://DENTIST-SERVICE"))
            .route("appointment-service", r -> r.path("/appointments/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://APPOINTMENT-SERVICE"))
            .route("treatment-service", r -> r.path("/treatments/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://TREATMENT-SERVICE"))
            .route("billing-service", r -> r.path("/billing/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://BILLING-SERVICE"))
            .route("notification-service", r -> r.path("/notifications/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://NOTIFICATION-SERVICE"))
            .route("reporting-service", r -> r.path("/reports/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://REPORTING-SERVICE"))
            .build();
    }
}