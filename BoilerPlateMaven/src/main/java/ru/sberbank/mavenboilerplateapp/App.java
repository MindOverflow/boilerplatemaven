package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.garret.perst.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App extends Persistent {
    public static final String FILENAME = "/Users/z17105624/source/repos/boilerplatemaven/books.xml";

    public static void main(String[] args) {
        System.out.println("Hello");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(FILENAME));
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();

            System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
            System.out.println("------");
            NodeList list = document.getElementsByTagName("book");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String author = element.getElementsByTagName("author").item(0).getTextContent();
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String genre = element.getElementsByTagName("genre").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String publishDate = element.getElementsByTagName("publish_date").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    System.out.println("Current Element :" + node.getNodeName());
                    System.out.println("Book Id : " + id);
                    System.out.println("Author : " + author);
                    System.out.println("Title : " + title);
                    System.out.println("Genre" + genre);
                    System.out.println("Price : " + price);
                    System.out.println("Publish Date : " + publishDate);
                    System.out.println("Description : " + description);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("The end");
    }
}
