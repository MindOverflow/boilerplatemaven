package ru.sberbank.mavenboilerplateapp;
import org.garret.perst.FieldIndex;
import org.garret.perst.Index;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

// There should be one root object in the database, containing
// collections used to access all other objects in the storage.
public class MyRootClass extends Persistent
{
    // index on MyPersistentClass.intKey
    public FieldIndex<MyPersistentClass> intKeyIndex;
    // index on MyPersistentClass.strKey
    public FieldIndex<MyPersistentClass> strKeyIndex;
    // index on MyPersistentClass, which key doesn’t
    // belong to the class
    public Index<MyPersistentClass> foreignIndex;
    public MyRootClass(Storage db) {
        super(db);
        intKeyIndex = db.<MyPersistentClass>createFieldIndex(
                MyPersistentClass.class, // indexed class
                "intKey", // name of indexed field
                true); // unique index
        strKeyIndex = db.<MyPersistentClass>createFieldIndex(
                MyPersistentClass.class, // indexed class
                "strKey", // name of indexed field
                false); // index allows duplicates (is not unique)
        foreignIndex = db.<MyPersistentClass>createIndex(
                int.class, // key type
                false); // index allows duplicates (is not unique)
    }
    // Default constructor is needed for Perst to be able to
    // instantiate instances of loaded objects
    public MyRootClass() {}
}