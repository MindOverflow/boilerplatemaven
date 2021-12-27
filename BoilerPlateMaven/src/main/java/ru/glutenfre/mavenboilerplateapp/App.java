package ru.glutenfre.mavenboilerplateapp;

import org.garret.perst.Storage;
import org.garret.perst.StorageFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;


public class App
{
    public static void main(String[] args) throws JAXBException, IOException {
        System.out.println("Starting...");
        // JAXBContext представляет как бы клиентскую входную точку для JAXB API
        // По умолчанию, JAXB не форматирует XML-документ
        JAXBContext context = JAXBContext.newInstance(Catalog.class);

        Catalog catalog = (Catalog) context.createUnmarshaller().unmarshal(new FileReader("./books.xml"));
        // System.out.println(catalog.toString());

        Storage db = StorageFactory.getInstance().createStorage();
        db.open("my_pretty_perst_db.dbs");
        Catalog root = (Catalog)db.getRoot();
        if (root == null) {
            // Root is not yet defined: storage not initialized
            root = new Catalog(db); // create root object
            db.setRoot(root); // register root object
        }
        int books_count = catalog.getBooks().size();
        for (int i = 0; i < books_count; i++) {
            Book book = new Book();
            Book input_book = catalog.getBooks().get(i);

            book.setId(input_book.getId());
            book.setAuthor(input_book.getAuthor());
            book.setTitle(input_book.getTitle());
            book.setGenre(input_book.getGenre());
            book.setPrice(input_book.getPrice());
            book.setPublish_date(input_book.getPublish_date());
            book.setDescription(input_book.getDescription());

            root.idIndex.put(book);
            root.authorIndex.put(book);
            root.titleIndex.put(book);
            root.genreIndex.put(book);
            root.priceIndex.put(book);
            root.publish_dateIndex.put(book);
            root.descriptionIndex.put(book);
        }
        String uploadPath = "./dbexport.xml";
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
        FileInputStream fileInputStream = new FileInputStream(uploadPath);

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        System.out.println(resultStringBuilder.toString());
        db.close();
        System.out.println("The end");
    }
}