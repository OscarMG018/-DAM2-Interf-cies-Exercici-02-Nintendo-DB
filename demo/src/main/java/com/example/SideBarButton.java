package com.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class SideBarButton extends Button {

    String text;
    int index;
    Object object;
    Consumer<Integer> function;
    public SideBarButton(String text, int index, Object object, Consumer<Integer> function) {
        super("");
        this.object = object;
        this.index = index;
        this.text = text;
        this.function = function;
        this.setOnAction(event -> {
            function.accept(index);
        });
        this.getStyleClass().add("sidebar-button");

        SetGraphic();
    }

    public void SetGraphic() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        ImageView imatge = null;
        try {
            if (object instanceof Consola) {
                imatge = new ImageView(getClass().getResource("/assets/images/" + ((Consola) object).getImatge()).toURI().toString());
            }
            else if (object instanceof Personatge) {
                imatge = new ImageView(getClass().getResource("/assets/images/" + ((Personatge) object).getImatge()).toURI().toString());
            }
            else if (object instanceof Joc) {
                imatge = new ImageView(getClass().getResource("/assets/images/" + ((Joc) object).getImatge()).toURI().toString());
            }
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        imatge.setFitWidth(40);
        imatge.setFitHeight(40);
        hbox.getChildren().add(imatge);
        Label label = new Label(text);
        label.getStyleClass().add("sidebar-button-text");
        hbox.getChildren().add(label);
        this.setGraphic(hbox);
        this.setPrefWidth(721);
    }
}