package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Slf4j
public class App
{
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        log.info("Starting...");

        // JAXBContext представляет как бы клиентскую входную точку для JAXB API
        // По умолчанию, JAXB не форматирует XML-документ
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();

        Book book = (Book) context.createUnmarshaller().unmarshal(new FileReader("./books.xml"));
        log.info(book.toString());
        log.info("The end");
    }
}