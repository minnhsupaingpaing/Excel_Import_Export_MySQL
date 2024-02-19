package com.fortune.broadband.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;


import java.util.Date;
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activation_date")
    private Date activationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "status_change_date")
    private Date statusChangeDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_account_number")
    private String customerAccountNumber;

    @Column(name = "billing_account_number")
    private String billingAccountNumber;

    @Column(name = "customer_category")
    private String customerCategory;

    @Column(name = "plan_status")
    private String planStatus;

    @Column(name = "plan_name")
    private String planName;

    private String region;
    private String phoneNo;
    private String emailId;

    @Column(name = "billing_address")
    private String billingAddress;

}
