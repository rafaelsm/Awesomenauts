package models;

/**
 * Created by Rafael on 10/5/14.
 */
public enum AttackType {

    MELEE("Melee"),
    SHORT_RANGE("Short range"),
    MEDIUM_RANGE("Medium range"),
    LONG_RANGE("Long range");

    private String label;

    AttackType(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
