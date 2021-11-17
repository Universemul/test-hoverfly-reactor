package com.example.demo.config;

import com.example.demo.handler.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public WebClient helloworldWebClient() {
        return WebClient.builder().baseUrl("https://docs.openaq.org/").build();
    }
    @Bean
    public HelloWorldHandler helloWorldHandler(WebClient helloworldWebClient) {
        return new HelloWorldHandler(helloworldWebClient);
    }
}
