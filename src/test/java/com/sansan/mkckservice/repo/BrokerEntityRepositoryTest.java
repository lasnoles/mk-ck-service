package com.sansan.mkckservice.repo;

import com.sansan.mkckservice.repo.model.BrokerEntity;
import com.sansan.mkckservice.utils.TestDataUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrokerEntityRepositoryTest {

    @Autowired
    private BrokerRepository testObject;

    private BrokerEntity brokerEntity = TestDataUtils.getBroker(true);

    public void insert_data(){
        testObject.save(brokerEntity).block();
    }

    @After
    public void cleanUp(){
        testObject.delete(brokerEntity).block();
    }

    @Test
    public void when_insert_data_then_can_retrieve_it(){
        insert_data();
        assertThat(testObject.existsById(brokerEntity.getBrokerCode()).block(), is(true));
    }

    @Test(expected = TransientDataAccessResourceException.class)
    public void when_update_nonexist_data_then(){
        testObject.save(TestDataUtils.getBroker(false)).block();
    }

    @Test
    public void when_query_with_paganition_then_return_as_expected(){
        insert_data();
        Flux<BrokerEntity> byBrokerCodeLike = testObject.findByBrokerCodeLike(brokerEntity.getBrokerCode(),
                PageRequest.of(0, 10, Sort.Direction.ASC, "brokerCode"));
        StepVerifier.create(byBrokerCodeLike).expectNext(brokerEntity).expectComplete();
    }
}
