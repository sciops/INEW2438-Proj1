/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.project1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Stephen R. Williams
 * 
 * handler class for a catalog of CDs
 */
public class CatalogHandler extends DefaultHandler {

    private List<CompactDisc> catalog = null;
    private CompactDisc cd = null;

    //simple get method to get the list of CDs
    public List<CompactDisc> getCatalog() {
        return catalog;
    }

    boolean bTitle = false;
    boolean bArtist = false;
    boolean bCountry = false;
    boolean bCompany = false;
    boolean bPrice = false;
    boolean bYear = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("CD")) {
            cd = new CompactDisc();

            //initialize list
            if (catalog == null) {
                catalog = new ArrayList();
            }
        } else if (qName.equalsIgnoreCase("title")) {
            bTitle = true;
        } else if (qName.equalsIgnoreCase("artist")) {
            bArtist = true;
        } else if (qName.equalsIgnoreCase("country")) {
            bCountry = true;
        } else if (qName.equalsIgnoreCase("company")) {
            bCompany = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("year")) {
            bYear = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("cd")) {
            catalog.add(cd);//finished with CD, add it to the catalog
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bYear) {
            //age element, set Employee age
            cd.setYear(Integer.parseInt(new String(ch, start, length)));
            bYear = false;
        } else if (bTitle) {
            cd.setTitle(new String(ch, start, length));
            bTitle = false;
        } else if (bArtist) {
            cd.setArtist(new String(ch, start, length));
            bArtist = false;
        } else if (bCountry) {
            cd.setCountry(new String(ch, start, length));
            bCountry = false;
        } else if (bCompany) {
            cd.setCompany(new String(ch, start, length));
            bCompany = false;
        } else if (bPrice) {
            cd.setPrice(Double.parseDouble(new String(ch, start, length)));
            bPrice = false;
        }

    }

}
