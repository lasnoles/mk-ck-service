package com.sansan.mkckservice.service;

import com.sansan.mkckservice.repo.BrokerRepository;
import com.sansan.mkckservice.rest.OperationStatus;
import com.sansan.mkckservice.rest.model.Broker;
import com.sansan.mkckservice.utils.TestDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.TransientDataAccessResourceException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void test_createBroker_success(){
        when(brokerRepository.save(any())).thenReturn(Mono.just(TestDataUtils.getBroker(false)));
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

        StepVerifier.create(status).expectError(TransientDataAccessResourceException.class).verify();
    }
}
