package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.io.File;
import java.net.URISyntaxException;
import javafx.scene.image.ImageView;


public class ListItemViewController implements Initializable {
    @FXML
    private Label Title;

    @FXML
    private VBox ItemBox;

    @FXML
    private Button ReturnButton;

    private JsonLoader jsonLoader;

    private Listas listType;


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

    public void LoadItems(Listas listas){
        listType = listas;
        ItemBox.getChildren().clear();
        Title.setText(ToTitle(listas.toString()));
        switch(listas){
            case CONSOLES:
                ArrayList<Consola> consoles = jsonLoader.getConsoles();
                for (int i = 0; i < consoles.size(); i++) {
                    SideBarButton button = new SideBarButton(consoles.get(i).getNom(), i, consoles.get(i),this::LoadItem);
                    ItemBox.getChildren().add(button);
                }
                break;
            case PERSONATGES:
                ArrayList<Personatge> personatges = jsonLoader.getPersonatges();
                for (int i = 0; i < personatges.size(); i++) {
                    SideBarButton button = new SideBarButton(personatges.get(i).getNom(), i, personatges.get(i),this::LoadItem);
                    ItemBox.getChildren().add(button);
                }
                break;
            case JOCS:
                ArrayList<Joc> jocs = jsonLoader.getJocs();
                for (int i = 0; i < jocs.size(); i++) {
                    SideBarButton button = new SideBarButton(jocs.get(i).getNom(), i, jocs.get(i),this::LoadItem);
                    ItemBox.getChildren().add(button);
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    private void LoadItem(int index) {
        ItemViewController controller = (ItemViewController) UtilsViews.getController("MobileItem");
        controller.LoadItem(listType, index);
        UtilsViews.setViewAnimating("MobileItem");
    }

    public void Return() {
        UtilsViews.setViewAnimating("MobileLists");
    }
}
    