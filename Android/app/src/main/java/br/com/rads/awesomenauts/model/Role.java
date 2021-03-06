package br.com.rads.awesomenauts.model;

/**
 * Created by rafael_2 on 18/02/14.
 */
public enum Role {

    AREA_CONTROL("Area Control"),
    ASSASSIN("Assassin"),
    CROWD_CONTROL("Crowd Control"),
    DAMAGE_DEALER("Damage Dealer"),
    FIGHTER("Fighter"),
    HARASSER("Harasser"),
    INITIATOR("Initiator"),
    NUKER("Nuker"),
    PUSHER("Pusher"),
    SUPPORT("Support"),
    TANK("Tank");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
