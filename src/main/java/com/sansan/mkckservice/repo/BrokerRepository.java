package com.sansan.mkckservice.repo;

import com.sansan.mkckservice.repo.model.Broker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BrokerRepository extends ReactiveCrudRepository<Broker, String> {
}
