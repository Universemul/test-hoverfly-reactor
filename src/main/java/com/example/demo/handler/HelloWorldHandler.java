package com.example.demo.handler;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class HelloWorldHandler {

    private final WebClient helloworldWebClient;

    public Mono<String> compute() {
        return helloworldWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("ping").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
