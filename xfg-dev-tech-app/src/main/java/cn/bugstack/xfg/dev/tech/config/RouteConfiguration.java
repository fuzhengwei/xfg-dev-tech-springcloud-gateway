package cn.bugstack.xfg.dev.tech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@EnableConfigurationProperties(RouteConfiguration.UriConfiguration.class)
@RestController
public class RouteConfiguration {

    @Bean
    public RouteLocator route(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String httpUri = uriConfiguration.getHttp();
        return builder.routes()
                .route(p -> p.path("/baidu").uri("https://www.baidu.com/"))
                .route(p -> p.path("/bugstack").uri("https://bugstack.cn/md/road-map/road-map.html"))
                .route(p -> p.path("/error").uri("forward:/fallback"))
                .route(p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri(httpUri))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

    @ConfigurationProperties
    static class UriConfiguration {

        private String http = "http://gaga.plus";

        public String getHttp() {
            return http;
        }

        public void setHttp(String http) {
            this.http = http;
        }

    }

}

