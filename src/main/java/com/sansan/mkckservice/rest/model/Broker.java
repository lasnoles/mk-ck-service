package com.sansan.mkckservice.rest.model;

import com.sansan.mkckservice.repo.model.BrokerEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
public class Broker {
    private String brokerCode;
    private String brokerName;
    private String createdBy;
    private Date createdOn;
    private String approvedBy;
    private Date approvedOn;

    public Broker(String brokerCode, String brokerName) {
        this.brokerCode = brokerCode;
        this.brokerName = brokerName;
    }

    public String getKey() {
        return brokerCode;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Broker broker = (Broker) o;
        return Objects.equals(brokerCode, broker.brokerCode) && Objects.equals(brokerName, broker.brokerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brokerCode, brokerName);
    }

    public static Broker buildBroker(BrokerEntity brokerEntity){
        Broker broker = new Broker();
        broker.brokerCode = brokerEntity.getBrokerCode();
        broker.brokerName = brokerEntity.getBrokerName();
        broker.createdBy = brokerEntity.getUpdatedBy();
        broker.createdOn = brokerEntity.getUpdatedOn();
        broker.approvedBy = brokerEntity.getApprovedBy();
        broker.approvedOn = brokerEntity.getUpdatedOn();
        return broker;

    }
}
