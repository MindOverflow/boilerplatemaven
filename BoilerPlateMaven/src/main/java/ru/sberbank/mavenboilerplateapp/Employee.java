package ru.sberbank.mavenboilerplateapp;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "firstName", "lastName", "income" })
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private double income;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getIncome() {
        return income;
    }


    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
