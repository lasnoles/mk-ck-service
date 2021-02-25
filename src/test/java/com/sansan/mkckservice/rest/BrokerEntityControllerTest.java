package com.sansan.mkckservice.rest;

import com.sansan.mkckservice.rest.model.Broker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebFluxTest
public class BrokerEntityControllerTest {

    @Autowired
    private WebTestClient webFluxTest;

    @Test
    public void create_broker(){

    }

    @Test
    public void update_broker(){

    }

    @Test
    public void get_brokers(){
        Flux<Broker> brokerFlux = webFluxTest.get().uri("flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk().returnResult(Broker.class).getResponseBody();

        StepVerifier.create(brokerFlux).expectSubscription()
                .expectNext(new Broker("AAA","BBB"))
                .verifyComplete();

    }

    @Test
    public void delete_broker(){

    }
}
