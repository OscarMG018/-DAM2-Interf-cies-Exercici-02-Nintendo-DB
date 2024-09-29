package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {

    JsonLoader jsonLoader;
    @FXML
    private Label Title;

    @FXML
    private VBox ItemBox;

    @FXML
    private Button ReturnButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jsonLoader = JsonLoader.getInstance();
        try {
            File file = new File(getClass().getResource("/assets/images/returnButton.png").toURI());
            ImageView image = new ImageView(file.toURI().toString());
            image.setFitHeight(30);
            image.setFitWidth(30);
            image.setPreserveRatio(true);
            ReturnButton.setGraphic(image);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String ToTitle(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public void LoadItem(Listas listas, int index) {
        ItemBox.getChildren().clear();
        switch (listas) {
            case JOCS:
                Joc joc = jsonLoader.getJocs().get(index);
                Title.setText(ToTitle(joc.getNom()));
                ItemBox.getChildren().addAll(joc.ToNodes(300, 400));
                break;
            case PERSONATGES:
                Personatge personatge = jsonLoader.getPersonatges().get(index);
                Title.setText(ToTitle(personatge.getNom()));
                ItemBox.getChildren().addAll(personatge.ToNodes(300, 400));
                break;
            case CONSOLES:
                Consola consola = jsonLoader.getConsoles().get(index);
                Title.setText(ToTitle(consola.getNom()));
                ItemBox.getChildren().addAll(consola.ToNodes(300, 400));
                break;
        }
    }

    public void Return() {
        UtilsViews.setViewAnimating("MobileListItem");
    }

}
    