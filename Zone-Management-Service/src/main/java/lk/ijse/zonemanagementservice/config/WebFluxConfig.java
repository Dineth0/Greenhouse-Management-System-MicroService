package lk.ijse.zonemanagementservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;

@Configuration
public class WebFluxConfig {

    @Value("${iot.api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
