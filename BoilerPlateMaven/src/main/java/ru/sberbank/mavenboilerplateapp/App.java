package ru.sberbank.mavenboilerplateapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.*;


@Slf4j
public class App
{
    public static void main(String[] args) throws JAXBException, IOException, ClassNotFoundException {
        log.info("Starting...");

        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(
            new File("./books.json"),
            new TypeReference<Root>() {
            }
        );

        log.info("The end");
    }
}