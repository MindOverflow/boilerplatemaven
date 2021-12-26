package ru.sberbank.mavenboilerplateapp;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookCsv {
    @CsvBindByName(column = "id")
    //@CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByName(column = "author")
    //@CsvBindByPosition(position = 1)
    private String author;
    @CsvBindByName(column = "title")
    //@CsvBindByPosition(position = 2)
    private String title;
    @CsvBindByName(column = "genre")
    //@CsvBindByPosition(position = 3)
    private String genre;
    @CsvBindByName(column = "price")
    //@CsvBindByPosition(position = 4)
    private String price;
    @CsvBindByName(column = "publish_date")
    //@CsvBindByPosition(position = 5)
    private String publish_date;
    @CsvBindByName(column = "description")
    //@CsvBindByPosition(position = 6)
    private String description;

    public BookCsv() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
