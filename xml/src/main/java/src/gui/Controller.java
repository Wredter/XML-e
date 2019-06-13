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
    @FXML
    public ListView<String> listView;
    @FXML
    public TextField nazwa = new TextField();
    @FXML
    public TextField cena = new TextField();
    @FXML
    public TextField kalorycznosc = new TextField();
    @FXML
    public ComboBox skladnik1 = new ComboBox();
    @FXML
    public ComboBox skladnik2 = new ComboBox();
    @FXML
    public ComboBox skladnik3 = new ComboBox();
    @FXML
    public ComboBox skladnik4 = new ComboBox();
    @FXML
    public ComboBox skladnik5 = new ComboBox();
    @FXML
    public ComboBox skladnik6 = new ComboBox();
    @FXML
    public ComboBox skladnik7 = new ComboBox();
    @FXML
    public ComboBox skladnik8 = new ComboBox();
    @FXML
    public DatePicker data = new DatePicker();
    @FXML
    public TextField imie1 = new TextField();
    @FXML
    public TextField imie2 = new TextField();
    @FXML
    public TextField nazwisko1 = new TextField();
    @FXML
    public TextField nazwisko2 = new TextField();
    @FXML
    public TextField projekt = new TextField();
    @FXML
    public DatePicker modyfikacja = new DatePicker();
    @FXML
    public TextField path_save = new TextField();
    @FXML
    public TextField path_read = new TextField();
    @FXML
    public TextField skladnik_nazwa = new TextField();
    @FXML
    public TextField skladnik_id = new TextField();
    @FXML
    public ListView<String> skladnik_list = new ListView<>();

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

