package api.modsen.library.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bookService", r -> r.path("/books/**")
                        .filters(f -> f.filter(new AuthorizationHeaderFilter())) // использование фильтра
                        .uri("lb://book-microservice"))
                .route("authService", r -> r.path("/auth/**")
                        .uri("lb://auth-microservice"))
                .build();
    }
}