package com.example.android.horizontalpaging;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Worksite implements Serializable {
    /* Kuvaa työmaata. Serialisoinnilla pyritään mahdollistamaan intent.putExtra() tämän luokan
     * olion suhteen. Jos serialisointi on hidasta, niin sen sijasta voidaan käyttää Parceable
     * implementaatiota.
     * Lähde:
     * http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
     */
    private String name;
    private String date;
    private Integer fileImage;
    private ArrayList<Drawing> drawings;
    private ArrayList<Instruction> instructions;

    public Worksite(String name, String date, Integer fileImage, ArrayList<Drawing>drawings,
                    ArrayList<Instruction> instructions) {
        // Työmaa oliolla on nimi, päivämäärä (kansiot), piirustuksia, ohjeita ja kansion
        // kuva (R.drawable viite).
        this.name = name;
        this.date = date;
        this.drawings = drawings;
        this.instructions = instructions;
        this.fileImage = fileImage;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Integer getFileImage() {
        return fileImage;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public ArrayList<Drawing> getDrawings() {
        return drawings;
    }
}
