package ru.glutenfre.mavenboilerplateapp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.garret.perst.FieldIndex;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

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

    public FieldIndex<Book> idIndex;
    public FieldIndex<Book> authorIndex;
    public FieldIndex<Book> titleIndex;
    public FieldIndex<Book> genreIndex;
    public FieldIndex<Book> priceIndex;
    public FieldIndex<Book> publish_dateIndex;
    public FieldIndex<Book> descriptionIndex;

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