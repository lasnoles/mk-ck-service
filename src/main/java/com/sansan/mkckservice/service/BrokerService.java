package com.sansan.mkckservice.service;

import com.sansan.mkckservice.repo.BrokerRepository;
import com.sansan.mkckservice.repo.Status;
import com.sansan.mkckservice.repo.model.BrokerEntity;
import com.sansan.mkckservice.rest.OperationStatus;
import com.sansan.mkckservice.rest.model.Broker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class BrokerService {
    private BrokerRepository brokerRepository;

    public BrokerService(BrokerRepository brokerRepository) {
        this.brokerRepository = brokerRepository;
    }

    public Mono<OperationStatus> createBroker(Broker broker, String maker, boolean isNew) {
        Timestamp now = new Timestamp(new Date().getTime());
        BrokerEntity brokerEntity = new BrokerEntity();
        brokerEntity.setBrokerCode(broker.getBrokerCode());
        brokerEntity.setBrokerName(broker.getBrokerName());
        brokerEntity.setStatus(Status.PENDING.name());
        brokerEntity.setUpdatedBy(maker);
        brokerEntity.setUpdatedOn(now);
        brokerEntity.setNew(isNew);
        return brokerRepository.save(brokerEntity)
                .thenReturn(OperationStatus.builder().status(OperationStatus.Status.Success).build());

    }

    public Mono<Broker> approveBroker(String brokerCode, String checker) {
        BrokerEntity entity = brokerRepository.findById(brokerCode).map(e -> approve(e, checker)).block();
        return brokerRepository.save(entity).map(b -> new Broker(b.getBrokerCode(), b.getBrokerName()));
    }

    private BrokerEntity approve(BrokerEntity entity, String checker) {
        entity.setStatus(entity.getStatus().equalsIgnoreCase(Status.PENDING.name()) ? Status.APPROVED.name() : Status.CANCELLING.name());
        entity.setApprovedBy(checker);
        entity.setApprovedOn(new Timestamp(new Date().getTime()));
        return entity;
    }

    public Mono<Broker> cancelBroker(String brokerCode, String maker) {
        BrokerEntity entity = brokerRepository.findById(brokerCode).map(e -> approve(e, maker)).block();
        return brokerRepository.save(entity).map(b -> new Broker(b.getBrokerCode(), b.getBrokerName()));
    }

    private BrokerEntity cancel(BrokerEntity entity, String maker) {
        entity.setStatus(Status.CANCELLING.name());
        entity.setUpdatedBy(maker);
        entity.setUpdatedOn(new Timestamp(new Date().getTime()));
        return entity;
    }

    public Flux<Broker> getAllBrokers(String brokerCodeLike) {
        //TODO
        return null;
    }
}
