package ru.sberbank.mavenboilerplateapp;

import org.garret.perst.FieldIndex;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

import java.util.List;

public class Catalog extends Persistent {

    public FieldIndex<Book> books;
    public Catalog() { }
    public Catalog(Storage db) {
        super(db);
        books = db.createFieldIndex(Book.class, "id", true);
    }

}
