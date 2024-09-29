package com.example;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import java.util.Arrays;

public class Consola {
    private String nom;
    private String data;
    private String procesador;
    private String color;
    private int venudes;
    private String imatge;

    public Consola(String nom, String data, String procesador, String color, int venudes, String imatge) {
        this.nom = nom;
        this.data = data;
        this.procesador = procesador;
        this.color = color;
        this.venudes = venudes;
        this.imatge = imatge;
    }
    
    public String getNom() {
        return nom;
    }

    public String getData() {
        return data;
    }

    public String getProcesador() {
        return procesador;
    }

    public String getColor() {
        return color;
    }

    public int getVenudes() {
        return venudes;
    }

    public String getImatge() {
        return imatge;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setVenudes(int venudes) {
        this.venudes = venudes;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }    

    public ArrayList<Node> ToNodes(int width, int height) {
        Label nomLabel = new Label(nom);
        nomLabel.getStyleClass().add("item-title");
        Label dataLabel = new Label(String.valueOf(data));
        dataLabel.getStyleClass().add("item-info");
        Label procesadorLabel = new Label(String.valueOf(procesador));
        procesadorLabel.getStyleClass().add("item-info");
        Label colorLabel = new Label(color);
        colorLabel.getStyleClass().add("item-info");
        Label venudesLabel = new Label(String.valueOf(venudes));
        venudesLabel.getStyleClass().add("item-info");
        ImageView imatge = null;
        try {
            imatge = new ImageView(getClass().getResource("/assets/images/" + this.imatge).toURI().toString()); 
            System.out.println(imatge.getImage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        imatge.setFitHeight(height/2f);
        imatge.setFitWidth(width/2f);
        imatge.setPreserveRatio(true);
        return new ArrayList<Node>(Arrays.asList(nomLabel, imatge, dataLabel,venudesLabel, procesadorLabel, colorLabel));
    }
}
