package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

/**
 * В данном проекте описано подключение логгра, самое простое и элегантное! *
 */
@Slf4j
public class App
{
    public static void main(String[] args) {
        log.info("Hello Slf4j logger and World!");
    }
}
