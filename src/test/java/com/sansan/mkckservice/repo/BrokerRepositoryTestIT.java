package com.sansan.mkckservice.repo;

import com.sansan.mkckservice.repo.model.Broker;
import com.sansan.mkckservice.utils.TestDataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrokerRepositoryTestIT {

    @Autowired
    private BrokerRepository testObject;

    @Test
    public void when_insert_then_as_expected(){
        Broker broker = TestDataUtils.getBroker();
        testObject.save(broker).block();

        assertThat(testObject.existsById(broker.getBrokerCode()).block(), is(true));
    }
}
