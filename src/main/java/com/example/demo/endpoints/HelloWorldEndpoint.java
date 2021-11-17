package com.example.demo.endpoints;

import com.example.demo.handler.HelloWorldHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/helloworld")
@RequiredArgsConstructor
public class HelloWorldEndpoint {

    private final HelloWorldHandler helloWorldHandler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getHelloWorld() {
        return helloWorldHandler.compute().map(ResponseEntity::ok);
    }
}
