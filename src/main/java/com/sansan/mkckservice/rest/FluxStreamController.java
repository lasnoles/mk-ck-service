package com.sansan.mkckservice.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxStreamController {
    @RequestMapping(value = "/stream/api/numbers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getIntegers(){
        return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1));
    }

    @RequestMapping(value = "/api/numbers")
    public Flux<Integer> getNormalIntegers(){
        return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1));
    }
}
