package com.sansan.mkckservice.repo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("broker")
@Data
@EqualsAndHashCode(exclude = {"isNew"})
public class BrokerEntity implements Persistable<String> {

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

    @Transient
    private boolean isNew;

    @Override
    public String getId() {
        return brokerCode;
    }

    public boolean isNew(){
        return isNew;
    }

}
