package com.example.android.horizontalpaging;

import java.io.Serializable;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Instruction implements Serializable{
    private String title, text;
    private Integer instructionId;
    //...

    public Instruction(String title, Integer instructionId, String text) {
        this.title = title;
        this.instructionId = instructionId;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text; }

    public Integer getInstructionId() {
        return instructionId;
    }
}
