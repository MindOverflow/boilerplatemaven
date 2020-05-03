package ru.sberbank.mavenboilerplateapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String country;
    private String city;
    private String street;
    private Long homeNumber;
    private Long flatNumber;
}
