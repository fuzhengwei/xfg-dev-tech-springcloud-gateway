package cn.bugstack.xfg.dev.tech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 资料；
 * 1. 官网；https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#glossary
 * 2. Sentinel 流量网关 https://sentinelguard.io/zh-cn/docs/api-gateway-flow-control.html
 * 3. 匹配；https://start.spring.io/actuator/info
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"httpbin=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
public class ApiTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void contextLoads() {
        webClient.get().uri("/get").exchange().expectStatus().isOk().expectBody().jsonPath("$.headers.Hello").isEqualTo("World");
    }

}
