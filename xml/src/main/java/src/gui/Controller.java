package src.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import src.classes.RootType;
import src.logic.XMLOperations;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.List;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {
    //listy
    @FXML
    public ListView<String> listaPlaylist; //lista playlist - piosenka
    @FXML
    public ListView<String> listaPiosenek;
    @FXML
    public ListView<String> listaGatunkow;
    @FXML
    public ListView<String> listaWykonawcow; //lista wykonawcow - artysta
    @FXML
    public ListView<String> listaArtystow;

    //lista piosenek - piosenka
    @FXML
    public TextField tytulPiosenki = new TextField();
    @FXML
    public TextField czasPiosenki = new TextField();
    @FXML
    public TextField albumPiosenki = new TextField();
    @FXML
    public TextField rokPiosenki = new TextField();
    @FXML
    public ComboBox nastrojPiosenki = new ComboBox();
    @FXML
    public ComboBox wykonawcaPiosenki = new ComboBox();
    @FXML
    public ComboBox gatunekPiosenki = new ComboBox();

    //lista artystow - artysta
    //TODO
    @FXML
    public TextField imieArtysty = new TextField();
    @FXML
    public TextField nazwiskoArtysty = new TextField();
    @FXML
    public TextField pseudoArtysty = new TextField();

    //lista gatunkow - gatunek
    //TODO
    @FXML
    public TextField id_gatunek = new TextField();
    @FXML
    public TextField nazwaGatunek = new TextField();
    //lists wykonawców
    @FXML
    public TextField id_wykonawcy = new TextField();
    @FXML
    public TextField nazwaWykonawcy = new TextField();

    private static RootType root = XMLOperations.root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            XMLOperations.readFromXML();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root = XMLOperations.root;
        mainTab();
    }

    public void mainTab() {
        ArrayList<String> skl = new ArrayList<>();
        skl.add("");

        skladnik1.setItems(FXCollections.observableArrayList(skl));
        skladnik2.setItems(FXCollections.observableArrayList(skl));
        skladnik3.setItems(FXCollections.observableArrayList(skl));
        skladnik4.setItems(FXCollections.observableArrayList(skl));
        skladnik5.setItems(FXCollections.observableArrayList(skl));
        skladnik6.setItems(FXCollections.observableArrayList(skl));
        skladnik7.setItems(FXCollections.observableArrayList(skl));
        skladnik8.setItems(FXCollections.observableArrayList(skl));
    }

    public void clear() {
        nazwa.clear();
        cena.clear();
        kalorycznosc.clear();
        clearSKL();
        System.out.println("clear");
    }

    private void clearSKL() {
        skladnik1.getSelectionModel().select(0);
        skladnik2.getSelectionModel().select(0);
        skladnik3.getSelectionModel().select(0);
        skladnik4.getSelectionModel().select(0);
        skladnik5.getSelectionModel().select(0);
        skladnik6.getSelectionModel().select(0);
        skladnik7.getSelectionModel().select(0);
        skladnik8.getSelectionModel().select(0);
    }




    private void saveXMLFile() {
        try {
            XMLOperations.saveToXML("burgerownia");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void openDifferentXMLFile() {
        String name = path_read.getText();
        XMLOperations.setXmlFilePath(name);
        try {
            XMLOperations.readFromXML();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void transformToXHTML(String path) {
        XMLOperations.transformXML(path);
    }


}

