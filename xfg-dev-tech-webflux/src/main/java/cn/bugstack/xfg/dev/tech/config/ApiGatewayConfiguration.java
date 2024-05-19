package cn.bugstack.xfg.dev.tech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ApiGatewayConfiguration {

    private final WebClient.Builder webClientBuilder;

    public ApiGatewayConfiguration(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * curl http://localhost:9091/wg/service1/8091
     * curl http://localhost:9091/wg/service2/8092
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/wg/service1/{id}"), this::service1Handler)
                .andRoute(GET("/wg/service2/{id}"), this::service2Handler);
    }

    public Mono<ServerResponse> service1Handler(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<String> response = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8091/api/user/hi?" + id)
                .retrieve()
                .bodyToMono(String.class);
        return ServerResponse.ok().body(response, String.class);
    }

    public Mono<ServerResponse> service2Handler(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<String> response = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8092/api/user/hi?" + id)
                .retrieve()
                .bodyToMono(String.class);
        return ServerResponse.ok().body(response, String.class);
    }

}
