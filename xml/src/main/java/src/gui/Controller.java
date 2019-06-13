package src.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import src.classes.*;
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
    public ListView<String> listaPlaylist = new ListView<>(); //lista playlist - piosenka
    @FXML
    public ListView<String> listaPiosenek = new ListView<>();
    @FXML
    public ListView<String> listaPiosenekRef = new ListView<>();
    @FXML
    public ListView<String> listaGatunkow = new ListView<>();
    @FXML
    public ListView<String> listaWykonawcow = new ListView<>(); //lista wykonawcow - artysta
    @FXML
    public ListView<String> listaArtystow = new ListView<>();


    //playlista
    @FXML
    public TextField nazwaPlaylisty = new TextField();

    //lista piosenek ref - piosenka ref
    @FXML
    public ComboBox idPiosenki = new ComboBox();

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

    @FXML
    public TextField path_read = new TextField();

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
        refreshListaPlaylist();
        refreshListaWykonawcow();
        refreshListaPiosenek();
        refreshListaGatunkow();
    }




    public void refreshListaGatunkow() {
        ArrayList<String> gatunki = new ArrayList<>();
        for (GatunekType gatunek : root.getListaGatunkow().getGatunek()) {
            gatunki.add(gatunek.getValue());
        }
        listaGatunkow.setItems(FXCollections.observableArrayList(gatunki));
    }

    public void refreshListaPlaylist() {
        ArrayList<String> playlisty = new ArrayList<>();
        for (PlajlistaType p : root.getPlajlista()) {
            playlisty.add(p.getNazwa());
        }
        listaPlaylist.setItems(FXCollections.observableArrayList(playlisty));
    }

    public void refreshListaPiosenekRef(int playlistaId) {
        ArrayList<String> piosenki = new ArrayList<>();
        for (PiosenkaReferenceType p : root.getPlajlista().get(playlistaId).getPiosenkaRef()) {
            piosenki.add(p.getTytulRef());
        }
        listaPiosenekRef.setItems(FXCollections.observableArrayList(piosenki));
    }

    public void refreshListaPiosenek() {
        ArrayList<String> piosenki = new ArrayList<>();
        for (PiosenkaType p : root.getListaPiosenek().getPiosenka()) {
            piosenki.add(p.getTytul());
        }
        idPiosenki.setItems(FXCollections.observableArrayList(piosenki));
        listaPiosenek.setItems(FXCollections.observableArrayList(piosenki));
    }

    public void refreshListaWykonawcow() {
        ArrayList<String> wykonawcy = new ArrayList<>();
        for (WykonawcaType p : root.getListaWykonawcow().getWykonawca()) {
            wykonawcy.add(p.getId());
        }
        listaWykonawcow.setItems(FXCollections.observableArrayList(wykonawcy));
    }

    public void refreshListaArtystow(int wykonawcaId) {
        ArrayList<String> artysci = new ArrayList<>();
        for (ArtystaType p : root.getListaWykonawcow().getWykonawca().get(wykonawcaId).getArtysta()) {
            artysci.add(p.getImie() + " " + p.getNazwisko());
        }
        listaArtystow.setItems(FXCollections.observableArrayList(artysci));
    }

    public void refreshPiosenka(int piosenkaId) {
        PiosenkaType p = (PiosenkaType) root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(listaPiosenek.getSelectionModel().getSelectedItem())).toArray()[0];
        tytulPiosenki.setText(p.getTytul());
        czasPiosenki.setText(p.getCzasTrwania());
        albumPiosenki.setText(p.getAlbum());
        rokPiosenki.setText(p.getRokWydania().toString());
        nastrojPiosenki.setId(p.getNastroj());
        wykonawcaPiosenki.setId(p.getWykonawca());
        gatunekPiosenki.setId(p.getGatunek());
    }





    public void selectPlaylista() {
        PlajlistaType p = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        int id = root.getPlajlista().indexOf(p);
        nazwaPlaylisty.setText(p.getNazwa());
        refreshListaPiosenekRef(id);
    }

    public void selectWykonawca() {
        WykonawcaType p = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        int id = root.getListaWykonawcow().getWykonawca().indexOf(p);
        refreshListaArtystow(id);
    }

    public void selectPiosenka() {
        PiosenkaType p = (PiosenkaType)root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(listaPiosenek.getSelectionModel().getSelectedItem())).toArray()[0];
        int id = root.getListaPiosenek().getPiosenka().indexOf(p);
        refreshPiosenka(id);
    }

    public void selectPiosenkaRef() {
//        PlajlistaType p = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
//        PiosenkaReferenceType pr = (PiosenkaReferenceType)p.getPiosenkaRef().stream().filter(x -> x.getTytulRef().equals(listaPiosenekRef.getSelectionModel().getSelectedItem())).toArray()[0];
//        int id = root.getListaPiosenek().getPiosenka().indexOf(p);
//        refreshPiosenka(id);
    }




    public void clickUsunPiosenkeZPlaylisty() {
        PlajlistaType pl = (PlajlistaType) root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaReferenceType pr = (PiosenkaReferenceType) pl.getPiosenkaRef().stream().filter(x -> x.getTytulRef().equals(listaPiosenekRef.getSelectionModel().getSelectedItem())).toArray()[0];
        pl.getPiosenkaRef().remove(pr);
        int id = pl.getPiosenkaRef().indexOf(pr);
        refreshListaPiosenekRef(id);
    }

    public void clickUsunPiosenke() {
        PiosenkaType p = (PiosenkaType)root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(listaPiosenek.getSelectionModel().getSelectedItem())).toArray()[0];
        root.getListaPiosenek().getPiosenka().remove(p);
        refreshListaPiosenek();
    }

    public void clickDodajPiosenke() {
        PiosenkaType p = new PiosenkaType();
        p.setAlbum(albumPiosenki.getText());
        p.setTytul(tytulPiosenki.getText());
        p.setCzasTrwania(czasPiosenki.getText());
        p.setRokWydania(new BigInteger(rokPiosenki.getText()));
        p.setGatunek(gatunekPiosenki.getPromptText());
        p.setWykonawca(wykonawcaPiosenki.getPromptText());
        p.setNastroj(nastrojPiosenki.getPromptText());
        root.getListaPiosenek().getPiosenka().add(p);
        refreshListaPiosenek();
    }

    public void clickUpdatePiosenke() {
        PiosenkaType p = (PiosenkaType)root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(listaPiosenek.getSelectionModel().getSelectedItem())).toArray()[0];
        p.setAlbum(albumPiosenki.getText());
        p.setTytul(tytulPiosenki.getText());
        p.setCzasTrwania(czasPiosenki.getText());
        p.setRokWydania(new BigInteger(rokPiosenki.getText()));
        p.setGatunek(gatunekPiosenki.getPromptText());
        p.setWykonawca(wykonawcaPiosenki.getPromptText());
        p.setNastroj(nastrojPiosenki.getPromptText());
        refreshListaPiosenek();
    }

    public void clickDodajPiosenkeDoPlaylisty() {
        PlajlistaType pl = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaType p = (PiosenkaType)root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(idPiosenki.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaReferenceType pr = new PiosenkaReferenceType();
        pr.setTytulRef(p.getTytul());
        pl.getPiosenkaRef().add(pr);
        int id = pl.getPiosenkaRef().indexOf(pr);
        refreshListaPiosenekRef(id);
    }

    public void clickDodajPlayliste() {
        PlajlistaType p = new PlajlistaType();
        p.setNazwa(nazwaPlaylisty.getText());
        root.getPlajlista().add(p);
        refreshListaPlaylist();
    }

    public void clickUsunPlayliste() {
        PlajlistaType pl = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        root.getPlajlista().remove(pl);
        refreshListaPlaylist();
    }

    public void clickUpdatePlayliste() {
        PlajlistaType pl = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        pl.setNazwa(nazwaPlaylisty.getText());
        refreshListaPlaylist();
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

