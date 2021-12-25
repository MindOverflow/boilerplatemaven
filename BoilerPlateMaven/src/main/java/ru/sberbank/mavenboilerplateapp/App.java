package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.garret.perst.*;

import java.io.*;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App extends Persistent {
    public static void main(String[] args) {
//        // get instance of the storage
//        Storage db = StorageFactory.getInstance().createStorage();
//        // open the database
//        db.open("test.dbs");

//        MyRootClass root = (MyRootClass)db.getRoot();
//        // get storage root
//        if (root == null) {
//            // Root is not yet defined: storage not initialized
//            root = new MyRootClass(db); // create root object
//            db.setRoot(root); // register root object
//        }

//        // Create new object instance
//        MyPersistentClass obj = new MyPersistentClass();
//        obj.intKey = 1;
//        obj.strKey = "A.B";
//        obj.body = "Hello world";
//
//        // and insert it in the corresponding indexes
//        root.intKeyIndex.put(obj);
//        // add object to index on intKey field
//        root.strKeyIndex.put(obj);
//        // add object to index in strKey field
//        root.foreignIndex.put(new Key(1001), obj);
//        db.close(); // close the storage

//        Index<MyPersistentClass> dictionary = (Index<MyPersistentClass>)db.getRoot(); // get storage root
//        if (dictionary == null) {
//            // Root is not yet defined: storage is not initialized
//            dictionary = db.createIndex(String.class, // key type
//                    true); // unique index
//        }
//        // Now we can get persistent object by string identifiers:
//        MyPersistentClass obj = new MyPersistentClass(10, "stringKey", "bodyKey");
//        // and store it in the storage with specified key binding:
//        dictionary.put("yet-another-object", obj);
//
//        MyPersistentClass obj1 = (MyPersistentClass)dictionary.get("yet-another-object");
//        int a = 10;
//
//        String uploadPath ="/Users/z17105624/source/repos/boilerplatemaven/test.xml";
//        try {
//            Writer writer = new BufferedWriter(new FileWriter(uploadPath));
//            // export the whole database to the specified writer in XML
//            // format
//            db.exportXML(writer);
//            // exportXML doesn't close the stream, close it here
//            writer.close();
//        } catch (IOException x) {
//            System.err.println("Export failed: " + x);
//        }

//        Catalog root = (Catalog) db.getRoot();// get storage root
//        if (root == null) {
//            // Root is not yet defined: storage is not initialized
//            root = new Catalog(db); // create root object
//            db.setRoot(root); // register root object
//        }
//        String file ="/Users/z17105624/source/repos/boilerplatemaven/books.xml";
//        FileReader fileReader;
//        try {
//            fileReader = new FileReader(file);
//            db.importXML(fileReader);
//            fileReader.close();
//        } catch (XMLImportException | IOException e) {
//            e.printStackTrace();
//        }

        // get instance of the storage
        Storage db = StorageFactory.getInstance().createStorage();
        // open the database
        db.open("test.dbs");
        MyRootClass root = (MyRootClass)db.getRoot();
        // get storage root
        if (root == null) {
            // Root is not yet defined: storage not initialized
            root = new MyRootClass(db); // create root object
            db.setRoot(root); // register root object
        }
        // Create new object instance
        MyPersistentClass obj = new MyPersistentClass();
        obj.intKey = 1;
        obj.strKey = "A.B";
        obj.body = "Hello world 1";
         // ... and insert it in the corresponding indexes
        root.intKeyIndex.put(obj);
        // add object to index on intKey field
        root.strKeyIndex.put(obj);
        // add object to index in strKey field
        root.foreignIndex.put(new Key(1001), obj);

        MyPersistentClass obj2 = new MyPersistentClass();
        obj2.intKey = 2;
        obj2.strKey = "A.C";
        obj2.body = "Hello world 2";
        root.intKeyIndex.put(obj2);
        root.strKeyIndex.put(obj2);
        root.foreignIndex.put(new Key(1002), obj2);

        MyPersistentClass obj3 = new MyPersistentClass();
        obj3.intKey = 3;
        obj3.strKey = "A.Z";
        obj3.body = "Hello world 3";
        root.intKeyIndex.put(obj3);
        root.strKeyIndex.put(obj3);
        root.foreignIndex.put(new Key(1003), obj3);

        MyPersistentClass obj4 = new MyPersistentClass();
        obj4.intKey = 4;
        obj4.strKey = "A.Y";
        obj4.body = "Hello world 4";
        root.intKeyIndex.put(obj4);
        root.strKeyIndex.put(obj4);
        root.foreignIndex.put(new Key(1004), obj4);

        MyPersistentClass obj5 = new MyPersistentClass();
        obj5.intKey = 5;
        obj5.strKey = "A.X";
        obj5.body = "Hello world 5";
        root.intKeyIndex.put(obj5);
        root.strKeyIndex.put(obj5);
        root.foreignIndex.put(new Key(1005), obj5);

        String uploadPath ="/Users/z17105624/source/repos/boilerplatemaven/uploading.xml";
        try {
            Writer writer = new BufferedWriter(new FileWriter(uploadPath));
            // export the whole database to the specified writer in XML
            // format
            db.exportXML(writer);
            // exportXML doesn't close the stream, close it here
            writer.close();
        } catch (IOException x) {
            System.err.println("Export failed: " + x);
        }
        log.info("It is to be to end...");
        db.close();
    }
}
