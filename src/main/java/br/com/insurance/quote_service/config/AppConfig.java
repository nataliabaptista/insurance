package br.com.insurance.quote_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
@Setter
public class AppConfig {

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

}
