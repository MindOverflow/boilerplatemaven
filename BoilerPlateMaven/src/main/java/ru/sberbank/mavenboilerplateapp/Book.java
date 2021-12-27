package ru.sberbank.mavenboilerplateapp;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.garret.perst.Persistent;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book extends Persistent {
    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publish_date;
    private String description;

    @JsonProperty("id")
    @JsonGetter("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("author")
    @JsonGetter("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    @JsonSetter("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("title")
    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("genre")
    @JsonGetter("genre")
    public String getGenre() {
        return genre;
    }

    @JsonProperty("genre")
    @JsonSetter("genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("price")
    @JsonGetter("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    @JsonSetter("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("publish_date")
    @JsonGetter("publish_date")
    public String getPublish_date() {
        return publish_date;
    }

    @JsonProperty("publish_date")
    @JsonSetter("publish_date")
    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    @JsonProperty("description")
    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

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
}
