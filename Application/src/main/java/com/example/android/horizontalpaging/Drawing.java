package com.example.android.horizontalpaging;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Drawing {
    private String title;
    private Integer drawingId;
    //...

    public Drawing(String title, Integer drawingId) {
        this.title = title;
        this.drawingId = drawingId;

    }

    public String getTitle() {
        return title;
    }

    public Integer getDrawingId() {
        return drawingId;
    }
}
