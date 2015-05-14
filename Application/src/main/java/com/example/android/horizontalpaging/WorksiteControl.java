package com.example.android.horizontalpaging;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 *
 * Tämä luokka luo uusia olioita työmaista. Työmaille luodaan piirustukset ja ohjeet -olioita
 * erillisiin tietorakenteisiin
 */
public class WorksiteControl implements Serializable{
    private ArrayList<Worksite> worksites;  // Kaikkien työmaiden tietorakenne.

    public WorksiteControl(int amount) {
        // Alustaja kutsuu luokan metodia työmaiden luomiseksi.
        pupulateWorksites(amount);
    }

    // Luodaan uusia työmaita luokan tietorakenteeseen,
    private void pupulateWorksites(int amount) {
        worksites = new ArrayList<>();

        for(int i = 0; i < amount; i++)
            worksites.add(
                    new Worksite("Tyomaa " + (i + 1), i + ".2", R.drawable.file_512x512,
                            createDrawings(), createInstructions()));
    }

    // Lisätään uusia piirustuksia luokan tietorakenteeseen.
    private ArrayList<Drawing> createDrawings() {
        ArrayList<Drawing> drawings = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            drawings.add(new Drawing("Piiri " + (i + 1), R.drawable.circuit_sample,
                    R.drawable.circuit_schematic));
        }
        return drawings;
    }

    // Lisätään uusia ohjeita luokan tietorakenteeseen
    private ArrayList<Instruction> createInstructions() {
        ArrayList<Instruction> instructions = new ArrayList<>();
        String text = "";

        for(int i = 0; i < 5; i++) {
            // Ohjeella on kuvateksti, kuva ja itse tekstimuotoinen ohje.
            instructions.add(new Instruction("Ohje " + (i + 1), R.drawable.instruction_sample,
                    "Tämä on ohje."));
        }
        return instructions;
    }

    /* Etsii anettua nimeä karkeasti (String.contains()) vastaavat työmaat. Voi palauttaa tyhjän
     * listan (empty).
     */
    public ArrayList<Worksite> findWorksites(String name) {
        ArrayList<Worksite> foundWorksites = new ArrayList<>();

        for(Worksite worksite : worksites) {
            if(worksite.getName().toLowerCase().contains(name.toLowerCase())) {
                foundWorksites.add(worksite);

            }
        }

        return foundWorksites;
    }

    // Saantimetodit.

    public ArrayList<Worksite> getWorksites() {
        return worksites;
    }
}
