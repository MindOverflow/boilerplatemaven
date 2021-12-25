package ru.sberbank.mavenboilerplateapp;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "name", "date" })
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    /**
     * setters
     */
    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient // Такой атрибут указывает на то, что мы не хотим включать данное поле в XML
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * getters
     */
    public Long getId() {
        return this.id;
    }

    public String getName () {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
