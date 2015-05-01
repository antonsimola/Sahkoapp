package com.example.android.horizontalpaging;

import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 */
public class Worksite {
    private String name;
    private String date;
    private ArrayList<Drawing> drawings;
    private ArrayList<Instruction> instructions;

    public Worksite(String name, String date, ArrayList<Drawing>drawings,
                    ArrayList<Instruction> instructions) {
        this.name = name;
        this.date = date;
        this.drawings = drawings;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public ArrayList<Drawing> getDrawings() {
        return drawings;
    }
}
