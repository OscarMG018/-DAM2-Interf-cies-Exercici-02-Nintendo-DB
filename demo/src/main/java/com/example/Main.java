package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.beans.value.*;


public class Main extends Application {

    final int WINDOW_WIDTH = 650;
    final int WINDOW_HEIGHT = 450;
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega la vista inicial des del fitxer FXML
        UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
        UtilsViews.addView(getClass(), "MobileLists", "/assets/layout_mobile_lists.fxml");
        UtilsViews.addView(getClass(), "MobileListItem", "/assets/layout_mobile_list_items.fxml");
        UtilsViews.addView(getClass(), "MobileItem", "/assets/layout_mobile_item.fxml");
        UtilsViews.addView(getClass(), "Desktop", "/assets/layout_desktop.fxml");
        scene = new Scene(UtilsViews.parentContainer);
        scene.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldWidth, Number newWidth) {
                _setLayout(newWidth.intValue());
            }
        });

        stage.setScene(scene);
        stage.setTitle("JavaFX App");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.show();


        // Afegeix una icona només si no és un Mac
        if (!System.getProperty("os.name").contains("Mac")) {
            Image icon = new Image("file:icons/icon.png");
            stage.getIcons().add(icon);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void _setLayout(int width) {
        if (width < 600) {
            if (UtilsViews.activeView.equals("MobileLists"))
                return;
            if (UtilsViews.activeView.equals("MobileListItem"))
                return;
            if (UtilsViews.activeView.equals("MobileItem"))
                return;
            UtilsViews.setView("MobileLists");
            scene.getStylesheets().clear();
            scene.getStylesheets().add("/assets/layout_mobile.css");
        } else {
            UtilsViews.setView("Desktop");
            scene.getStylesheets().clear();
            scene.getStylesheets().add("/assets/layout_desktop.css");
        }
    }
}
