package com.sansan.mkckservice.rest;

import com.sansan.mkckservice.rest.model.Broker;
import com.sansan.mkckservice.service.BrokerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/brokers")
public class BrokerController {
    private BrokerService brokerService;

    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("")
    public Flux<Broker> getBrokers(@RequestParam Optional<String> brokerCode) {
        return brokerService.getAllBrokers(brokerCode);//TODO
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Broker> getBrokerStream() {
        return brokerService.getAllBrokers(Optional.empty());//TODO
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
