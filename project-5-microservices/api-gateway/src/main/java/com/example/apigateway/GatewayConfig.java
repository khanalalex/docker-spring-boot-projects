package com.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return route("auth_route")
                .route(path("/api/auth/**"), http())
                .before(uri("http://auth-service:8080"))
                .build()
            .and(route("book_route")
                .route(path("/api/books/**"), http())
                .before(uri("http://book-service:8080"))
                .build())
            .and(route("order_route")
                .route(path("/api/orders/**"), http())
                .before(uri("http://order-service:8080"))
                .build());
    }
}