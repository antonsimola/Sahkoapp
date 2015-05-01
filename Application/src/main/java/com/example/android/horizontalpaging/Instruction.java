package com.example.android.horizontalpaging;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Instruction {
    private String title;
    private Integer instructionId;
    //...

    public Instruction(String title, Integer instructionId) {
        this.title = title;
        this.instructionId = instructionId;

    }

    public String getTitle() {
        return title;
    }

    public Integer getInstructionId() {
        return instructionId;
    }
}
