package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.*;
import java.util.ArrayList;
import java.net.URISyntaxException;
import javafx.scene.text.*;


enum Listas {
    CONSOLES,
    PERSONATGES,
    JOCS
}

public class Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private VBox sideBar;

    @FXML
    private VBox ItemInfo;

    JsonLoader jsonLoader = JsonLoader.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JsonLoader jsonLoader = JsonLoader.getInstance();
        String[] list = { "Consoles", "Personatges", "Jocs" };
        choiceBox.getItems().addAll(list);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SwitchList(Listas.valueOf(newValue.toUpperCase()));

        });
        choiceBox.getSelectionModel().selectFirst();
    }

    public void SwitchList(Listas lista) {
        LoadSideBar(lista);
        switch (lista) {
            case CONSOLES:
                LoadConsolaInfo(0);
                break;
            case PERSONATGES:
                LoadPersonatgeInfo(0);
                break;
            case JOCS:
                LoadJocInfo(0);
                break;
        }
    }

    public void LoadSideBar(Listas lista) {
        sideBar.getChildren().clear();
        switch (lista) {
            case CONSOLES:
                ArrayList<Consola> consoles = jsonLoader.getConsoles();
                for (int i = 0; i < consoles.size(); i++) {
                    SideBarButton button = new SideBarButton(consoles.get(i).getNom(), i, consoles.get(i),this::LoadConsolaInfo);
                    sideBar.getChildren().add(button);
                }
                break;
            case PERSONATGES:
                ArrayList<Personatge> personatges = jsonLoader.getPersonatges();
                for (int i = 0; i < personatges.size(); i++) {
                    SideBarButton button = new SideBarButton(personatges.get(i).getNom(), i, personatges.get(i),this::LoadPersonatgeInfo);
                    sideBar.getChildren().add(button);
                }
                break;
            case JOCS:
                ArrayList<Joc> jocs = jsonLoader.getJocs();
                for (int i = 0; i < jocs.size(); i++) {
                    SideBarButton button = new SideBarButton(jocs.get(i).getNom(), i, jocs.get(i),this::LoadJocInfo);
                    sideBar.getChildren().add(button);
                }
                break;
        }
    }

    public void LoadJocInfo(int index) {
        ItemInfo.getChildren().clear();
        Joc joc = jsonLoader.getJocs().get(index);
        ItemInfo.getChildren().addAll(joc.ToNodes(600, 400));
    }

    public void LoadPersonatgeInfo(int index) {
        ItemInfo.getChildren().clear();
        Personatge personatge = jsonLoader.getPersonatges().get(index);
        ItemInfo.getChildren().addAll(personatge.ToNodes(600, 400));
    }

    public void LoadConsolaInfo(int index) {
        ItemInfo.getChildren().clear();
        Consola consola = jsonLoader.getConsoles().get(index);
        ItemInfo.getChildren().addAll(consola.ToNodes(600, 400));
    }
}
