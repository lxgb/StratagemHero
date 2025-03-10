package com.example.stratagemhero.stratagem_handler;

import java.util.ArrayList;

public class Stratagem {
    String stratagemName;
    int stratagemIcon;
    int comboCount;
    int[] comboImages = new int[8];
    String stratagemColor;
    ArrayList<String> comboPattern = new ArrayList<>();
    boolean isCompleted;

    public Stratagem() {}

    // define constructor
    public Stratagem(String stratagemName, int stratagemIcon, String stratagemColor, int[] comboImages, int comboCount, ArrayList<String> comboPattern, boolean isCompleted) {
        this.stratagemName = stratagemName;
        this.stratagemIcon = stratagemIcon;
        this.stratagemColor = stratagemColor;
        this.comboImages = comboImages;
        this.comboCount = comboCount;
        this.comboPattern = comboPattern;
        this.isCompleted = isCompleted;
    }

    // generic methods to call constructor data
    public String fetchStratagemName() {
        return stratagemName;
    }
    public void setStratagemName(String stratagemName) {
        this.stratagemName = stratagemName;
    }

    public int fetchStratagemIcon() {
        return stratagemIcon;
    }
    public void setStratagemIcon(int stratagemIcon) {
        this.stratagemIcon = stratagemIcon;
    }

    public String fetchStratagemColor() {
        return stratagemColor;
    }
    public void setStratagemColor(String stratagemColor) {
        this.stratagemColor = stratagemColor;
    }

    public int[] fetchComboImages() {
        return comboImages;
    }
    public void setComboImages(int[] comboImages) {
        this.comboImages = comboImages;
    }

    public int fetchComboCount() {
        return comboCount;
    }
    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }

    public ArrayList<String> fetchComboPattern() {
        return comboPattern;
    }
    public void setComboPattern(ArrayList<String> comboPattern) {
        this.comboPattern = comboPattern;
    }

    public boolean fetchIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
