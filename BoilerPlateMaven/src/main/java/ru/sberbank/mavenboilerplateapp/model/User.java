package ru.sberbank.mavenboilerplateapp.model;

import lombok.Data;

@Data
public class User {
    private Long age;
    private String name;
    private Address address;
}
