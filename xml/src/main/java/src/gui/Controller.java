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
    //file
    @FXML
    public TextField export = new TextField();
    @FXML
    public TextField save = new TextField();
    @FXML
    public TextField open = new TextField();


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
        refresh();
    }

    public void refresh() {
        root = XMLOperations.root;
        refreshListaPlaylist();
        refreshListaWykonawcow();
        refreshListaPiosenek();
        refreshListaGatunkow();
        refreshNastroj();
    }




    ArrayList<String> nastroje = new ArrayList<>();
    public void refreshNastroj() {
        nastroje.add("smutna");
        nastroje.add("mieszana");
        nastroje.add("wesola");
        nastroje.add("niezidentyfikowana");
        nastrojPiosenki.setItems(FXCollections.observableArrayList(nastroje));
    }

    public void refreshListaGatunkow() {
        ArrayList<String> gatunki = new ArrayList<>();
        for (GatunekType gatunek : root.getListaGatunkow().getGatunek()) {
            gatunki.add(gatunek.getId());
        }
        gatunekPiosenki.setItems(FXCollections.observableArrayList(gatunki));
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
        wykonawcaPiosenki.setItems(FXCollections.observableArrayList(wykonawcy));
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
        System.out.print(p.getNastroj());
        nastrojPiosenki.getSelectionModel().select(p.getNastroj());
        wykonawcaPiosenki.getSelectionModel().select(p.getWykonawca());
        gatunekPiosenki.getSelectionModel().select(p.getGatunek());
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
        nazwaWykonawcy.setText(p.getNazwa());
        id_wykonawcy.setText(p.getId());
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

    public void selectArtysta() {
        WykonawcaType pl = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        ArtystaType p = (ArtystaType) pl.getArtysta().stream().filter(x -> ((x.getImie() + " " + x.getNazwisko()).equals(listaArtystow.getSelectionModel().getSelectedItem()))).toArray()[0];
        imieArtysty.setText(p.getImie());
        nazwiskoArtysty.setText(p.getNazwisko());
        pseudoArtysty.setText(p.getPseudo());
    }

    public void selectGatunek() {
        GatunekType p = (GatunekType)root.getListaGatunkow().getGatunek().stream().filter(x -> x.getId().equals(listaGatunkow.getSelectionModel().getSelectedItem())).toArray()[0];
        id_gatunek.setText(p.getId());
        nazwaGatunek.setText(p.getValue());
        refreshListaGatunkow();
    }




    public void clickUsunPiosenkeZPlaylisty() {
        PlajlistaType pl = (PlajlistaType) root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaReferenceType pr = (PiosenkaReferenceType) pl.getPiosenkaRef().stream().filter(x -> x.getTytulRef().equals(listaPiosenekRef.getSelectionModel().getSelectedItem())).toArray()[0];
        pl.getPiosenkaRef().remove(pr);
        int id = root.getPlajlista().indexOf(pl);
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
        p.setGatunek(gatunekPiosenki.getValue().toString());
        p.setWykonawca(wykonawcaPiosenki.getValue().toString());
        p.setNastroj(nastrojPiosenki.getValue().toString());
        refreshListaPiosenek();
    }

    public void clickDodajPiosenkeDoPlaylisty() {
        PlajlistaType pl = (PlajlistaType)root.getPlajlista().stream().filter(x -> x.getNazwa().equals(listaPlaylist.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaType p = (PiosenkaType)root.getListaPiosenek().getPiosenka().stream().filter(x -> x.getTytul().equals(idPiosenki.getSelectionModel().getSelectedItem())).toArray()[0];
        PiosenkaReferenceType pr = new PiosenkaReferenceType();
        pr.setTytulRef(p.getTytul());
        pl.getPiosenkaRef().add(pr);
        int id = root.getPlajlista().indexOf(pl);
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

    public void clickDodajWykonawce() {
        WykonawcaType p = new WykonawcaType();
        p.setNazwa(nazwaWykonawcy.getText());
        p.setId(id_wykonawcy.getText());
        root.getListaWykonawcow().getWykonawca().add(p);
        refreshListaWykonawcow();
    }

    public void clickUsunWykonawce() {
        WykonawcaType pl = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        root.getListaWykonawcow().getWykonawca().remove(pl);
        refreshListaWykonawcow();
    }

    public void clickUpdateWykonawce() {
        WykonawcaType p = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        p.setNazwa(nazwaWykonawcy.getText());
        p.setId(id_wykonawcy.getText());
        refreshListaWykonawcow();
    }

    public void clickDodajArtyste() {
        ArtystaType p = new ArtystaType();
        p.setImie(imieArtysty.getText());
        p.setNazwisko(nazwiskoArtysty.getText());
        p.setPseudo(pseudoArtysty.getText());
        WykonawcaType pl = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        pl.getArtysta().add(p);
        int id = root.getListaWykonawcow().getWykonawca().indexOf(pl);
        refreshListaArtystow(id);
    }

    public void clickUsunArtyste() {
        WykonawcaType pl = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        ArtystaType a = (ArtystaType) pl.getArtysta().stream().filter(x -> ((x.getImie() + " " + x.getNazwisko()).equals(listaArtystow.getSelectionModel().getSelectedItem()))).toArray()[0];


        int id = root.getListaWykonawcow().getWykonawca().indexOf(pl);
        pl.getArtysta().remove(a);
        refreshListaArtystow(id);
    }

    public void clickUpdateArtyste() {
        WykonawcaType pl = (WykonawcaType)root.getListaWykonawcow().getWykonawca().stream().filter(x -> x.getId().equals(listaWykonawcow.getSelectionModel().getSelectedItem())).toArray()[0];
        ArtystaType p = (ArtystaType) pl.getArtysta().stream().filter(x -> ((x.getImie() + " " + x.getNazwisko()).equals(listaArtystow.getSelectionModel().getSelectedItem()))).toArray()[0];

        p.setImie(imieArtysty.getText());
        p.setNazwisko(nazwiskoArtysty.getText());
        p.setPseudo(pseudoArtysty.getText());
        int id = root.getListaWykonawcow().getWykonawca().indexOf(pl);
        refreshListaArtystow(id);
    }

    public void clickDodajGatunek() {
        GatunekType p = new GatunekType();
        p.setId(id_gatunek.getText());
        p.setValue(nazwaGatunek.getText());
        root.getListaGatunkow().getGatunek().add(p);
        refreshListaGatunkow();
    }

    public void clickUsunGatunek() {
        GatunekType pl = (GatunekType)root.getListaGatunkow().getGatunek().stream().filter(x -> x.getId().equals(listaGatunkow.getSelectionModel().getSelectedItem())).toArray()[0];
        root.getListaGatunkow().getGatunek().remove(pl);
        refreshListaGatunkow();
    }

    public void clickUpdateGatunek() {
        GatunekType p = (GatunekType)root.getListaGatunkow().getGatunek().stream().filter(x -> x.getId().equals(listaGatunkow.getSelectionModel().getSelectedItem())).toArray()[0];
        p.setId(id_gatunek.getText());
        p.setValue(nazwaGatunek.getText());
        refreshListaGatunkow();
    }



    public void saveXMLFile() {
        try {
            XMLOperations.saveToXML(save.getText());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void openDifferentXMLFile() {
        XMLOperations.setXmlFilePath(open.getText());
        try {
            XMLOperations.readFromXML();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        refresh();
    }

    public void transformToXHTML() {
        XMLOperations.transformXML(export.getText(), save.getText());
    }


}

