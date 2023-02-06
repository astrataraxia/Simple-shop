package com.project.domain;

import com.project.domain.constant.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@ToString
public class Delivery {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @ToString.Exclude
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded @Setter
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus status;  //READY COMPLETE CANCEL

}
