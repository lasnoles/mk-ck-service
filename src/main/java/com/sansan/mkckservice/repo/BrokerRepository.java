package com.sansan.mkckservice.repo;

import com.sansan.mkckservice.repo.model.BrokerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BrokerRepository extends ReactiveCrudRepository<BrokerEntity, String> {
    Flux<BrokerEntity> findByBrokerCodeLike(String brokerCode, Pageable pageable);
}
