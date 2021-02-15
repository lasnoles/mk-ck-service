package com.sansan.mkckservice.rest.model;

import java.util.Objects;

public class Broker {
    private String brokerCode;
    private String brokerName;

    public Broker(String brokerCode, String brokerName) {
        this.brokerCode = brokerCode;
        this.brokerName = brokerName;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public String getBrokerName() {
        return brokerName;
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
}
