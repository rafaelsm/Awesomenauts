package br.com.rads.awesomenauts.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static Awesomenaut parseJSON(String json){

        Awesomenaut awesomenaut = new Awesomenaut();

        try {
            JSONArray rootArray = new JSONArray(json);
            for (int i = 0; i < rootArray.length(); i++){
                JSONObject nautJson = rootArray.getJSONObject(i);
                awesomenaut.setName(nautJson.getString("name"));
                awesomenaut.setBackstory(nautJson.getString("backstory"));

                awesomenaut.setStats(parseStats(nautJson.getJSONObject("stats")));

                Log.d("AWESOMENAUT", awesomenaut.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return awesomenaut;
    }

    private static Stats parseStats(JSONObject stats) throws JSONException {
        return Stats.parseJSON(stats);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<String> getTipsPlayingAs() {
        return tipsPlayingAs;
    }

    public void setTipsPlayingAs(List<String> tipsPlayingAs) {
        this.tipsPlayingAs = tipsPlayingAs;
    }

    public List<String> getTipsPlayingAgainst() {
        return tipsPlayingAgainst;
    }

    public void setTipsPlayingAgainst(List<String> tipsPlayingAgainst) {
        this.tipsPlayingAgainst = tipsPlayingAgainst;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUnlockedAtLevel() {
        return unlockedAtLevel;
    }

    public void setUnlockedAtLevel(int unlockedAtLevel) {
        this.unlockedAtLevel = unlockedAtLevel;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.backstory;
    }
}
