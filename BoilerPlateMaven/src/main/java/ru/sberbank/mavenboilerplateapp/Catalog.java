package ru.sberbank.mavenboilerplateapp;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.garret.perst.FieldIndex;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Catalog extends Persistent {
    private List<Book> books;

    @JsonIgnore
    public FieldIndex<Book> idIndex;
    @JsonIgnore
    public FieldIndex<Book> authorIndex;
    @JsonIgnore
    public FieldIndex<Book> titleIndex;
    @JsonIgnore
    public FieldIndex<Book> genreIndex;
    @JsonIgnore
    public FieldIndex<Book> priceIndex;
    @JsonIgnore
    public FieldIndex<Book> publish_dateIndex;
    @JsonIgnore
    public FieldIndex<Book> descriptionIndex;

    @JsonProperty("book")
    @JsonGetter("book")
    public List<Book> getBook() {
        return books;
    }

    @JsonProperty("book")
    @JsonSetter("book")
    public void setBook(List<Book> books) {
        this.books = books;
    }

    public Catalog() {

    }

    public Catalog(List<Book> books) {
        this.books = books;
    }

    public Catalog(Storage db) {
        super(db);
        idIndex =
                db.<Book>createFieldIndex(Book.class, "id", true);
        authorIndex =
                db.<Book>createFieldIndex(Book.class, "author", false);
        titleIndex =
                db.<Book>createFieldIndex(Book.class, "title", false);
        genreIndex =
                db.<Book>createFieldIndex(Book.class, "genre", false);
        priceIndex =
                db.<Book>createFieldIndex(Book.class, "price", false);
        publish_dateIndex =
                db.<Book>createFieldIndex(Book.class, "publish_date", false);
        descriptionIndex =
                db.<Book>createFieldIndex(Book.class, "description", false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}