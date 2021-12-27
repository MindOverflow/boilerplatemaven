package ru.glutenfre.mavenboilerplateapp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.garret.perst.Persistent;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "author", "title", "genre", "price", "publish_date", "description" })
public class Book extends Persistent {
    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publish_date;
    private String description;

    public Book() {

    }
    public Book(String id, String author, String title, String genre, String price, String publish_date, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }
    @XmlElement(name = "genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }
    @XmlElement(name = "price")
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublish_date() {
        return publish_date;
    }
    @XmlElement(name = "publish_date")
    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[ id = " + getId() +
                ", author = " + getAuthor() +
                ", title = " + getTitle() +
                ", genre = " + getGenre() +
                ", price = " + getPrice() +
                ", publish_date = " + getPublish_date() +
                ", description = " + getDescription() + " ]";
    }

}
