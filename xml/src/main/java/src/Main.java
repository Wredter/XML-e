package src;

import src.logic.XMLOperations;

import javax.xml.bind.JAXBException;

/**
 * Created by Wojciech on 2019-06-13.
 */
public class Main {
    public static void main(String[] args) {
        try {
            XMLOperations.readFromXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
