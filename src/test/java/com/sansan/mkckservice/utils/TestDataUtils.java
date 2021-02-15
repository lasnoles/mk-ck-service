package com.sansan.mkckservice.utils;

import com.sansan.mkckservice.repo.Status;
import com.sansan.mkckservice.repo.model.BrokerEntity;

import java.sql.Timestamp;
import java.util.Date;

public class TestDataUtils {
    public static BrokerEntity getBroker(boolean isNew){
        Timestamp now = new Timestamp(new Date().getTime());
        BrokerEntity brokerEntity = new BrokerEntity();
        brokerEntity.setBrokerCode("code");
        brokerEntity.setBrokerName("name");
        brokerEntity.setStatus(Status.PENDING.name());
        brokerEntity.setUpdatedBy("tester1");
        brokerEntity.setUpdatedOn(now);
        brokerEntity.setApprovedBy("tester2");
        brokerEntity.setApprovedOn(now);
        brokerEntity.setNew(isNew);
        return brokerEntity;
    }
}
