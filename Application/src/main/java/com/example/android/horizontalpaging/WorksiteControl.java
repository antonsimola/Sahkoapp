package com.example.android.horizontalpaging;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 */
public class WorksiteControl implements Serializable{
    // Luon tällä työmaita, joilla on nimi, piirustuksia ja työohjeita. Piirustukset ja työohjeet
    // tulevat olemaan erillisiä olioita ArrayList-tietorakenteessa.

    private ArrayList<Worksite> worksites;

    public WorksiteControl(int amount) {
        pupulateWorksites(amount);
    }

    private void pupulateWorksites(int amount) {
        worksites = new ArrayList<>();

        // Tällä luodaan työmaat ja asetetaan ohjeet sekä piirustukset. Tästä voi tehdä erilaisia
        // toteutuksia (mitä käyttäjä näkee).
        for(int i = 0; i < amount; i++)
            worksites.add(
                    new Worksite("Tyomaa " + i, i + ".2", R.drawable.file_512x512,createDrawings(),
                            createInstructions()));
    }

    // Lisätään uusia piirustuksia ArrayList-tietorakenteeseen.
    private ArrayList<Drawing> createDrawings() {
        ArrayList<Drawing> drawings = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            drawings.add(new Drawing("Piiri " + i, R.drawable.circuit_sample));
        }
        return drawings;
    }

    // Lisätään uusia ohjeita ArrayList-tietorakenteeseen
    private ArrayList<Instruction> createInstructions() {
        ArrayList<Instruction> instructions = new ArrayList<>();
        String text = "";

        for(int i = 0; i < 5; i++) {
            // Ohjeella on kuvateksti, kuva ja itse tekstimuotoinen ohje.
            instructions.add(new Instruction("Ohje " + i, R.drawable.instruction_sample,
                    "Tämä on ohje."));
        }
        return instructions;
    }

    // Etsii anettua nimeä jokseenkin (String.contains()) vastaavat työmaat. Voi palauttaa tyhjän listan (empty).
    public ArrayList<Worksite> findWorksites(String name) {
        ArrayList<Worksite> foundWorksites = new ArrayList<>();

        for(Worksite worksite : worksites) {
            if(worksite.getName().toLowerCase().contains(name.toLowerCase())) {
                foundWorksites.add(worksite);

            }
        }

        return foundWorksites;
    }

    public ArrayList<Worksite> getWorksites() {
        return worksites;
    }
}
