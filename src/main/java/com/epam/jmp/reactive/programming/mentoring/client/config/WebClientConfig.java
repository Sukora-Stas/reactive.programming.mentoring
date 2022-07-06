package com.epam.jmp.reactive.programming.mentoring.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@EnableConfigurationProperties(WebClientProps.class)
public class WebClientConfig {

    @Bean
    public WebClient sportsAPIClient(WebClientProps props) {
        return WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .codecs(clientCodecConfigurer ->
                        clientCodecConfigurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
