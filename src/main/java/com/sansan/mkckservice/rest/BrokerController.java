package com.sansan.mkckservice.rest;

import com.sansan.mkckservice.rest.model.Broker;
import com.sansan.mkckservice.service.BrokerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/brokers")
public class BrokerController {
    private BrokerService brokerService;

    @GetMapping("")
    public Flux<Broker> getBrokers() {
        return null;//TODO
    }

    @PostMapping("")
    public Mono<OperationStatus> postBroker(@RequestBody Broker broker) {
        return brokerService.createBroker(broker, "TBA"/*TODO*/,true);
    }

    @PutMapping("")
    public Mono<OperationStatus> updateBroker(@RequestBody Broker broker) {
        return brokerService.createBroker(broker, "TBA", false);
    }
}
