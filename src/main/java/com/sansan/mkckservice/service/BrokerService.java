package com.sansan.mkckservice.service;

import com.sansan.mkckservice.repo.BrokerRepository;
import com.sansan.mkckservice.repo.Status;
import com.sansan.mkckservice.repo.model.BrokerEntity;
import com.sansan.mkckservice.rest.OperationStatus;
import com.sansan.mkckservice.rest.model.Broker;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

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
                .map(e -> OperationStatus.buildSuccess())
                .onErrorResume(e -> Mono.just(OperationStatus.buildFail(e.getMessage())));
    }

    public Mono<OperationStatus> approveBroker(String brokerCode, String checker) {
        return brokerRepository.findById(brokerCode).map(e -> approve(e, checker))
                .map(entity -> brokerRepository.save(entity))
                .map(e -> OperationStatus.buildSuccess())
                .switchIfEmpty(Mono.just(OperationStatus.buildFail("The Broker Code " + brokerCode + " doesn't exist")))
                .onErrorResume(e -> Mono.just(OperationStatus.buildFail(e.getMessage())));
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

    public Flux<Broker> getAllBrokers(String brokerCodeLike, PageRequest pageRequest) {
        return brokerRepository.findByBrokerCodeLike(brokerCodeLike, pageRequest)
                .filter(e->e.getStatus().equals(Status.APPROVED.name()))
                .map(Broker::buildBroker);
    }

    public Flux<Broker> getAllBrokers(Optional<String> brokerCodeLike) {
        return (brokerCodeLike.isPresent()?
                brokerRepository.findByBrokerCodeLike('%'+brokerCodeLike.get()+'%') :
                brokerRepository.findAll()).map(Broker::buildBroker);
    }
}
