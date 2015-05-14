package com.example.android.horizontalpaging;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 *
 * Tämä luokka kuvaa yksittäistä työmaata.
 */
public class Worksite implements Serializable {
    private String name;        // Työmaan nimi.
    private String date;        // Työmaan luontipäivämäärä (lisätty sovellukseen).
    private Integer fileImage;  // Työmaakansion kuvake.

    // Tietorakenteet työmaan piirustukseille ja ohjeille.
    private ArrayList<Drawing> drawings;
    private ArrayList<Instruction> instructions;

    public Worksite(String name, String date, Integer fileImage, ArrayList<Drawing>drawings,
                    ArrayList<Instruction> instructions) {
        this.name = name;
        this.date = date;
        this.drawings = drawings;
        this.instructions = instructions;
        this.fileImage = fileImage;
    }

    /* Etsii anettua otsikkoa karkeasti (String.contains()) vastaavat piirustukset. Voi palauttaa
     * tyhjän listan (empty).
     */
    public ArrayList<Drawing> findDrawings(String title) {
        ArrayList<Drawing> foundDrawings = new ArrayList<>();

        for(Drawing drawing : drawings) {
            if(drawing.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundDrawings.add(drawing);
            }
        }

        return foundDrawings;
    }

    /* Etsii anettua otsikkoa karkeasti (String.contains()) vastaavat ohjeet. Voi palauttaa
     * tyhjän listan (empty).
     */
    public ArrayList<Instruction> findInstructions(String title) {
        ArrayList<Instruction> foundInstructions = new ArrayList<>();

        for(Instruction instruction : instructions) {
            if(instruction.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundInstructions.add(instruction);
            }
        }

        return foundInstructions;
    }

    // Saanti- ja asetusmetodit.

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

    public void setDrawings(ArrayList<Drawing> drawings) {
        this.drawings.clear();
        this.drawings = drawings;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions.clear();
        this.instructions = instructions;
    }
}
