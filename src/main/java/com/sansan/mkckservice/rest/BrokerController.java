package com.sansan.mkckservice.rest;

import com.sansan.mkckservice.repo.BrokerRepository;
import com.sansan.mkckservice.repo.model.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/brokers")
public class BrokerController {
    @Autowired
    BrokerRepository brokerRepository;

    @GetMapping("")
    public Flux<Broker> getBrokers() {
        return brokerRepository.findAll();

    }

    @PostMapping("")
    public Mono<Broker> postBroker(@RequestBody Broker broker) {

        return brokerRepository.save(broker);
    }

    @PutMapping("")
    public Mono<Broker> updateBroker(@RequestBody Broker broker) {
        return brokerRepository.save(broker);
    }

    @DeleteMapping("")
    public boolean deleteBroker(@RequestBody Broker broker) {
        try {
            brokerRepository.deleteById(broker.getBrokerCode()).block(); // Note this!
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
