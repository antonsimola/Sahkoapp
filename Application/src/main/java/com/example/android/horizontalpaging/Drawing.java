package com.example.android.horizontalpaging;

import java.io.Serializable;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Drawing implements Serializable{
    private String title;
    private Integer drawingId;
    private Integer actualDrawingID;

    public Drawing(String title, Integer drawingId, Integer actualDrawingId) {
        this.title = title;
        this.drawingId = drawingId;
        this.actualDrawingID = actualDrawingId;

    }

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
