package com.sansan.mkckservice.repo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@ToString
public class Broker {
    @Id
    @Column("broker_code")
    private String brokerCode;
    @Column("broker_name")
    private String brokerName;
    @Column("status")
    private String status;
    @Column("updated_by")
    private String updatedBy;
    @Column("approved_by")
    private String approvedBy;
    @Column("updated_on")
    private Timestamp updatedOn;
    @Column("approved_on")
    private Timestamp approvedOn;
}
