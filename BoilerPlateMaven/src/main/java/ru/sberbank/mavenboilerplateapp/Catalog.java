package ru.sberbank.mavenboilerplateapp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.garret.perst.Persistent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog extends Persistent {
    @XmlElement(name = "book")
    private List<Book> books = null;
    public Catalog() {

    }
    public Catalog(List<Book> books) {
        this.books = books;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}