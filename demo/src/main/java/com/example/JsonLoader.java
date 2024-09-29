package com.example;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import org.json.*;

public class JsonLoader {
    public static JsonLoader instance;

    private File jocsJson;
    private File personatgesJson;
    private File consolesJson;

    private ArrayList<Joc> jocs;
    private ArrayList<Personatge> personatges;
    private ArrayList<Consola> consoles;

    private JsonLoader() {
        try {
            jocsJson = new File(getClass().getResource("/assets/data/jocs.json").toURI());
            personatgesJson = new File(getClass().getResource("/assets/data/personatges.json").toURI());
            consolesJson = new File(getClass().getResource("/assets/data/consoles.json").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        jocs = new ArrayList<>();
        personatges = new ArrayList<>();
        consoles = new ArrayList<>();
        LoadDB();
    }

    public static JsonLoader getInstance() {
        if (instance == null) {
            instance = new JsonLoader();
        }
        return instance;
    }

    public void LoadDB() {
        JSONArray json = null;
        String jsonString = "";
        //Consoles
        try {
            jsonString = new String(Files.readAllBytes(consolesJson.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject consola = json.getJSONObject(i);
            consoles.add(new Consola(consola.getString("nom"), consola.getString("data"), consola.getString("procesador"), consola.getString("color"), consola.getInt("venudes"), consola.getString("imatge")));
        }
        //Personatges
        try {
            jsonString = new String(Files.readAllBytes(personatgesJson.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject personatge = json.getJSONObject(i);
            personatges.add(new Personatge(personatge.getString("nom"), personatge.getString("imatge"), personatge.getString("color"), personatge.getString("nom_del_videojoc")));
        }
        //Jocs
        try {
            jsonString = new String(Files.readAllBytes(jocsJson.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject joc = json.getJSONObject(i);
            jocs.add(new Joc(joc.getString("nom"), joc.getInt("any"), joc.getString("tipus"), joc.getString("descripcio"), joc.getString("imatge")));
        }
    }

    public ArrayList<Joc> getJocs() {
        return jocs;
    }

    public ArrayList<Personatge> getPersonatges() {
        return personatges;
    }

    public ArrayList<Consola> getConsoles() {
        return consoles;
    }
}
