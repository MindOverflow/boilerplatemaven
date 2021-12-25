package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class App
{
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        log.info("Starting...");
        List<Employee> employeeList = new ArrayList<Employee>();

        Employee _Anton = new Employee();
        _Anton.setId(1);
        _Anton.setLastName("Коваленко");
        _Anton.setFirstName("Антон");
        _Anton.setIncome(100d);
        employeeList.add(_Anton);

        Employee _Alexandra = new Employee();
        _Alexandra.setId(2);
        _Alexandra.setLastName("Рыбцова");
        _Alexandra.setFirstName("Александра");
        _Alexandra.setIncome(100d);
        employeeList.add(_Alexandra);
        Employees employees = new Employees();
        employees.setEmployees(employeeList);

        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        // Для того чтобы вывод документа был форматированным, мы устанавливаем свойство
        // Marshaller.JAXB_FORMATTED_OUTPUT равным TRUE
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(employees, System.out);
        // Когда мы запустим код, мы можем проверить результат в файле book.xml
        marshaller.marshal(employees, new File("./employees.xml"));
        Employees employeesRead = (Employees) context.createUnmarshaller().unmarshal(new FileReader("./employees.xml"));
        log.info(employeesRead.toString());

        log.info("The end");
    }
}