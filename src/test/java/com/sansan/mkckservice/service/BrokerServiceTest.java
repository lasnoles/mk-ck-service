package com.sansan.mkckservice.service;

import com.sansan.mkckservice.repo.BrokerRepository;
import com.sansan.mkckservice.repo.model.BrokerEntity;
import com.sansan.mkckservice.rest.OperationStatus;
import com.sansan.mkckservice.rest.model.Broker;
import com.sansan.mkckservice.utils.TestDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrokerServiceTest {
    @Mock
    private BrokerRepository brokerRepository;
    private BrokerService testObject;

    @Before
    public void setUp(){
        testObject = new BrokerService(brokerRepository);

    }

    private void mockReturnFirstArgs() {
        when(brokerRepository.save(any())).thenAnswer((Answer) invocation -> {
            Object[] args = invocation.getArguments();
            return Mono.just((BrokerEntity) args[0]);
        });
    }

    @Test
    public void test_createBroker_success(){
        mockReturnFirstArgs();
        Broker expected = new Broker("test1", "test1");
        Mono<OperationStatus> status = testObject.createBroker(expected, "jessie", true);

        StepVerifier.create(status).expectNext(
                OperationStatus.builder().status(OperationStatus.Status.Success).build()).expectComplete();
    }

    @Test
    public void test_createBroker_failure(){
        when(brokerRepository.save(any())).thenReturn(
                Mono.error(new TransientDataAccessResourceException("unable to proceed")));
        Broker expected = new Broker("test1", "test2");
        Mono<OperationStatus> status = testObject.createBroker(expected, "jessie", false);

        StepVerifier.create(status).expectNext(OperationStatus.buildSuccess()).expectComplete();
    }

    @Test
    public void test_approveBroker_success(){
        mockReturnFirstArgs();
        BrokerEntity broker = TestDataUtils.getBroker(false);
        when(brokerRepository.findById(anyString())).thenReturn(Mono.just(broker));

        Mono<OperationStatus> approveBroker = testObject.approveBroker(broker.getBrokerCode(), "checker");

        StepVerifier.create(approveBroker).expectNext(OperationStatus.buildSuccess()).expectComplete();
    }

    @Test
    public void test_approveBroker_failure_when_brokercode_not_exists(){
        when(brokerRepository.findById(anyString())).thenReturn(Mono.empty());
        Mono<OperationStatus> approveBroker = testObject.approveBroker("XXX", "checker");

        StepVerifier.create(approveBroker).expectNext(OperationStatus.buildFail("The Broker Code XXX doesn't exist")).expectComplete();
    }

    @Test
    public void test_getBrokers_contains_expected(){
        BrokerEntity broker = TestDataUtils.getBroker(false);
        when(brokerRepository.findByBrokerCodeLike(any(), any())).thenReturn(Flux.just(broker));

        Flux<Broker> brokers = testObject.getAllBrokers("XXX",
                PageRequest.of(0, 10, Sort.Direction.ASC, "brokerCode"));

        StepVerifier.create(brokers).expectNext(new Broker(broker.getBrokerCode(), broker.getBrokerName())).expectComplete();
    }
}
