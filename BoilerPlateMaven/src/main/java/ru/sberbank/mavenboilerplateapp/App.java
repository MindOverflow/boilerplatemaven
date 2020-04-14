package ru.sberbank.mavenboilerplateapp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JAXBException {
        System.out.println( "Hello World!" );

        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setDate(new Date());

        // JAXBContext представляет как бы клиентскую входную точку для JAXB API
        // По умолчанию, JAXB не форматирует XML-документ
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        // Для того чтобы вывод вокумента был форматированным, мы устанавливаем свойство
        // Marshaller.JAXB_FORMATTED_OUTPUT равным TRUE
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Когда мы запустим код, мы можем проверить результат в файле book.xml
        marshaller.marshal(book, new File("./SampleBook.xml"));
    }
}
