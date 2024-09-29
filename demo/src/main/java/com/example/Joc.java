package com.example;

import java.net.URISyntaxException;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.Arrays;

public class Joc {
    private String nom;
    private int any;
    private String tipus;
    private String descripcio;
    private String imatge;

    public Joc(String nom, int any, String tipus, String descripcio, String imatge) {
        this.nom = nom;
        this.any = any;
        this.tipus = tipus;
        this.descripcio = descripcio;
        this.imatge = imatge;
    }

    public String getNom() {
        return nom;
    }

    public int getAny() {
        return any;
    }

    public String getTipus() {
        return tipus;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getImatge() {
        return imatge;
    }

    public ArrayList<Node> ToNodes(int width, int height){
        Label nomLabel = new Label(nom);
        nomLabel.getStyleClass().add("item-title");
        Label anyLabel = new Label(String.valueOf(any));
        anyLabel.getStyleClass().add("item-info");
        Label tipusLabel = new Label(tipus);
        tipusLabel.getStyleClass().add("item-info");
        Text descripcioLabel = new Text(descripcio);
        descripcioLabel.getStyleClass().add("item-info-wrapper");
        descripcioLabel.setWrappingWidth(width*0.6);
        ImageView imatge = null;
        try {
            imatge = new ImageView(getClass().getResource("/assets/images/" + this.imatge).toURI().toString());
            imatge.getStyleClass().add("item-image");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        imatge.setFitHeight(height/2f);
        imatge.setFitWidth(width/2f);
        imatge.setPreserveRatio(true);
        return new ArrayList<Node>(Arrays.asList(nomLabel, imatge, descripcioLabel,tipusLabel, anyLabel));
    }
}
