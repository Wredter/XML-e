package src;

import src.classes.RootType;
import src.logic.XMLOperations;

import javax.xml.bind.JAXBException;

/**
 * Created by Wojciech on 2019-06-13.
 */
public class Main {
    private static RootType root = XMLOperations.root;
    public static void main(String[] args) {
        try {
            XMLOperations.readFromXML();
            XMLOperations.saveToXML("elo");
            XMLOperations.transformXML("elohtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        root = XMLOperations.root;
        System.out.println(root.getListaGatunkow().getGatunek().get(0).getValue());
    }
}
