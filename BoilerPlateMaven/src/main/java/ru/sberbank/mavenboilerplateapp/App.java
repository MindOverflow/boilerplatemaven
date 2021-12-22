package ru.sberbank.mavenboilerplateapp;

import org.garret.perst.Storage;
import org.garret.perst.StorageFactory;
import org.garret.perst.XMLImportException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello World my dear Slf4j!
 */
public class App {
    public static void main(String[] args) {
        // get instance of the storage
        Storage db = StorageFactory.getInstance().createStorage();
        //open the database
        //int pagePoolSize = Integer.parseInt(args[1], 10); // size of page pol in bytes
        db.open("test.dbs");
        Object root = db.getRoot();
        // get storage root
        if (root == null) {
            // Root is not yet defined: storage is not initialized
            root = db.<Object>createIndex(String.class, true);
        }
        //import first file
//        Writer writer;
//        try {
//            writer = new BufferedWriter(new FileWriter("C:\\DB\\4\\FILES\\books.xml"));
//            db.exportXML(writer);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        String file ="/Users/z17105624/source/repos/boilerplatemaven/booksEmpty.xml";
        String line = null;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader;
        FileReader fileReader;
        try {
            //reader = new BufferedReader(new FileReader(file));
            fileReader = new FileReader(file);
//            while ( (line = reader.readLine()) != null) {
//                builder.append(line);
//            }
            db.importXML(fileReader);
            fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XMLImportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.close(); // ... and close it
    }
}
