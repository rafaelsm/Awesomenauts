package br.com.rads.awesomenauts.model;

/**
 * Created by rafael_2 on 18/02/14.
 */
public enum Mobility {

    AERIAL("Aerial"),
    BALANCED("Balanced"),
    SWIFT("Swift"),
    TACTICAL("Tactical");

    private String label;

    Mobility(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
