package com.project.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;


@Getter
@ToString
@Embeddable
public class Address {
    private String city;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String zipcode) {
        this.city = city;
        this.zipcode = zipcode;
    }
}
