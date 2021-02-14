package com.sansan.mkckservice.utils;

import com.sansan.mkckservice.repo.model.Broker;

import java.sql.Timestamp;
import java.util.Date;

public class TestDataUtils {
    public static Broker getBroker(){
        Timestamp now = new Timestamp(new Date().getTime());
        return new Broker("test-001", "test name 001","APPROVED",
                "tester1", "tester2", now, now);
    }
}
