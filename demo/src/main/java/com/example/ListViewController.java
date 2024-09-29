package com.example;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ListViewController implements Initializable {

    JsonLoader jsonLoader;

    @FXML
    private Button ConsolesButton;

    @FXML
    private Button PersonatgesButton;

    @FXML
    private Button JocsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jsonLoader = JsonLoader.getInstance();
        ConsolesButton.setOnAction(event -> handleConsolesButton(Listas.CONSOLES));
        PersonatgesButton.setOnAction(event -> handleConsolesButton(Listas.PERSONATGES));
        JocsButton.setOnAction(event -> handleConsolesButton(Listas.JOCS));
    }

    @FXML
    public void handleConsolesButton(Listas listType) {
        ListItemViewController controller = (ListItemViewController) UtilsViews.getController("MobileListItem");
        controller.LoadItems(listType);
        UtilsViews.setViewAnimating("MobileListItem");
    }
}

    
    
