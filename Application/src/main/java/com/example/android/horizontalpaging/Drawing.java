package com.example.android.horizontalpaging;

import java.io.Serializable;

/**
 * Created by Samuli on 2.5.2015.
 * Tämä luokka kuvaa yksittäiseen sähköpiirustukseen liittyvän kuvan ominaisuudet ja metodit.
 */
public class Drawing implements Serializable{
    private String title;               // Kuvaotsikko.
    private Integer drawingId;          // Viite kuvatiedoston kuvakkeeseen (res/drawlable).
    private Integer actualDrawingID;    // Viite todelliseen kuvaan (res/drawable).

    // Luokan alustaja ottaa prametrina kuvaotsikon sekä kuvakkeen ja todellisen kuvan resurssin.
    public Drawing(String title, Integer drawingId, Integer actualDrawingId) {
        this.title = title;
        this.drawingId = drawingId;
        this.actualDrawingID = actualDrawingId;

    }

    // Saantimetodit.
    public String getTitle() {
        return title;
    }

    public Integer getDrawingId() {
        return drawingId;
    }

    public Integer getActualDrawingID() {
        return actualDrawingID;
    }
}
