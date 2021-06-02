package pl.empik.empiktask.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.empik.empiktask.config.handler.RestTemplateResponseErrorHandler;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder, final RestTemplateResponseErrorHandler errorHandler) {
        return restTemplateBuilder.errorHandler(errorHandler).build();
    }

}
