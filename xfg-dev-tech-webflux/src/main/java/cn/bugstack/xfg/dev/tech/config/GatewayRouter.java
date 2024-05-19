package cn.bugstack.xfg.dev.tech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class GatewayRouter {

    @Bean
    public RouterFunction<ServerResponse> routeToService() {
        return RouterFunctions
                .route(GET("/service1").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> ServerResponse.ok().bodyValue("Response from Service 1"))
                .andRoute(GET("/service2").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> ServerResponse.ok().bodyValue("Response from Service 2"));
    }

}
