package br.com.rads.awesomenauts.model;

import java.util.Date;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Awesomenaut {

    private String name;
    private String backstory;
    private Stats stats;
    private List<Skill> skills;
    private List<String> tipsPlayingAs;
    private List<String> tipsPlayingAgainst;
    private String icon;
    private String image;
    private int unlockedAtLevel;
    private Date releaseDate;
}
