package ru.sberbank.mavenboilerplateapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    public Address() {
        super();
    }

    private String country;
    private String city;
    private String street;
    private Long homeNumber;
    private Long flatNumber;
}
