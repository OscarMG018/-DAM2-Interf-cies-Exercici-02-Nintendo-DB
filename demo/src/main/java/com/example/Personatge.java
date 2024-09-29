package com.example;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class Personatge {
    private String nom;
    private String imatge;
    private String color;
    private String nom_del_videojoc;

    public Personatge(String nom, String imatge, String color, String nom_del_videojoc) {
        this.nom = nom;
        this.color = color;
        this.imatge = imatge;
        this.nom_del_videojoc = nom_del_videojoc;
    }

    public String getNom() {
        return nom;
    }

    public String getColor() {
        return color;
    }

    public String getImatge() {
        return imatge;
    }

    public String getNom_del_videojoc() {
        return nom_del_videojoc;
    }

    public ArrayList<Node> ToNodes(int width, int height) {
        Label nomLabel = new Label(nom);
        nomLabel.getStyleClass().add("item-title");
        Label colorLabel = new Label(color);
        colorLabel.getStyleClass().add("item-info");
        Label jocLabel = new Label(nom_del_videojoc);
        jocLabel.getStyleClass().add("item-info");
        ImageView imatge = null;
        try {   
            imatge = new ImageView(getClass().getResource("/assets/images/" + this.imatge).toURI().toString());
            imatge.getStyleClass().add("item-image");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        imatge.setPreserveRatio(true);
        return new ArrayList<Node>(Arrays.asList(nomLabel, imatge, jocLabel, colorLabel));
    }
}
