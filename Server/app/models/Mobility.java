package models;

/**
 * Created by Rafael on 10/5/14.
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
