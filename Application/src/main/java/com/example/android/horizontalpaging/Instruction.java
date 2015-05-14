package com.example.android.horizontalpaging;

import java.io.Serializable;

/**
 * Created by Samuli on 2.5.2015.
 * Tämä luokka kuvaa yksittäiseen sähköpiirustukseen liittyvän ohjeen ominaisuudet ja metodit.
 */
public class Instruction implements Serializable{
    private String title, text;     // Ohjeen otsikko ja itse ohjeteksti.
    private Integer instructionId;  // Viite ohjekuvakkeeseen (res/drawable).

    // Luokan alustaja ottaa prametrina ohjeen otsikon, sen resurssiviitteen ja itse ohjeen.
    public Instruction(String title, Integer instructionId, String text) {
        this.title = title;
        this.instructionId = instructionId;
        this.text = text;
    }

    // Saantimetodit.
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text; }

    public Integer getInstructionId() {
        return instructionId;
    }
}
