package br.com.rads.awesomenauts.model;

/**
 * Created by rafael_2 on 18/02/14.
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
