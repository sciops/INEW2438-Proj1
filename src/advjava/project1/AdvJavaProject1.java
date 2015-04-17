/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.project1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Stephen R. Williams Project One
 *
 * Assignment objectives
 * Write a program that do the followings:
 * Download the XML file from http://www.w3schools.com/xml/cd_catalog.xml 
 * Create a Class with attributes similar to the XML 
 * Parse the XML file using either SAX, or DOM java API 
 * Capture the root elements with its children elements
 * using java collections API, List of objects 
 * Finally, display the objects, in the collection, in a JTable, by creating a java swing frame work with a
 * panel, and JTable.
 * 
 * Self-imposed bonus: Use HTTP connection to grab the xml file from w3schools instead of using a local, static file.
 * 
 */
public class AdvJavaProject1 {
    
    final static String url = "http://www.w3schools.com/xml/cd_catalog.xml";

    //called by running this class's main method or the jframe class.
    public static List<CompactDisc> xmlParse1() throws IOException {
        List<CompactDisc> catOut = null;// to be returned
        try {
            //set up SAXparser
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            CatalogHandler handler = new CatalogHandler();//see CatalogHandler.java
            //call httpMethod to get xml in Java String format
            String xml = httpMethod(url);
            //JOptionPane.showMessageDialog(null, input);
            
            //instruct saxParser to parse the xml String into the handler
            saxParser.parse(new InputSource(new StringReader(xml)), handler);

            //get list to this method
            List<CompactDisc> catalog = handler.getCatalog();
            
            //loop for debugging
            for (CompactDisc cd : catalog) {
                System.out.println(cd.getTitle()+"\n");
            }
            
            //assignment for return statement below
            catOut=catalog;
            
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
         return catOut;//this return statement must be outside the try-catch (why?)
    }

    //fetches xml document from a website
    public static String httpMethod(String url_s) throws MalformedURLException, IOException {
        URLConnection connection = new URL(url_s).openConnection();
        InputStream inputStream = connection.getInputStream();   
        String inputStreamString = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
        return inputStreamString;
    }

    public static void main(String[] args) throws IOException {
        xmlParse1();
    }

}
