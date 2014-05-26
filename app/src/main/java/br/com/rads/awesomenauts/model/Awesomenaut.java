package br.com.rads.awesomenauts.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Awesomenaut {

    public static final String TAG = "AWESOMENAUT";

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

    public static List<Awesomenaut> parseJSON(String json){

        List<Awesomenaut> allNauts = new ArrayList<Awesomenaut>();

        try {
            JSONArray rootArray = new JSONArray(json);
            for (int i = 0; i < rootArray.length(); i++){

                JSONObject nautJson = rootArray.getJSONObject(i);

                Awesomenaut awesomenaut = new Awesomenaut();
                awesomenaut.name = nautJson.getString("name");
                awesomenaut.backstory = nautJson.getString("backstory");
                awesomenaut.stats = parseStats(nautJson.getJSONObject("stats"));
                awesomenaut.skills = parseSkills(nautJson.getJSONArray("skills"));
                awesomenaut.tipsPlayingAs = parseTips(nautJson.getJSONArray("tipsPlayingAs"));
                awesomenaut.tipsPlayingAgainst = parseTips(nautJson.getJSONArray("tipsPlayingAgainst"));
                awesomenaut.unlockedAtLevel = nautJson.getInt("unlockedAtLevel");
                awesomenaut.releaseDate = parseDateFromString(nautJson.getString("releaseDate"));

                allNauts.add(awesomenaut);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allNauts;
    }

    private static List<Skill> parseSkills(JSONArray skillArray) throws JSONException {
        return Skill.parseJSON(skillArray);
    }

    private static Stats parseStats(JSONObject statsJSON) throws JSONException {
        return Stats.parseJSON(statsJSON);
    }

    private static List<String> parseTips(JSONArray tipsArray) throws JSONException {

        List<String> tips = new ArrayList<String>();

        for (int i = 0; i < tipsArray.length(); i++){
            tips.add(tipsArray.getString(i));
        }

        return tips;
    }

    private static Date parseDateFromString(String releaseDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDrawableName() {

        StringBuilder drawableName = new StringBuilder( "ic_" );
        drawableName.append( this.name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø","o").replace("&","and") ) ;
        return drawableName.toString();
    }

    public String getName() {
        return name;
    }

    public String getBackstory() {
        return backstory;
    }

    public Stats getStats() {
        return stats;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<String> getTipsPlayingAs() {
        return tipsPlayingAs;
    }

    public List<String> getTipsPlayingAgainst() {
        return tipsPlayingAgainst;
    }

    public String getIcon() {
        return icon;
    }

    public String getImage() {
        StringBuilder imagePath = new StringBuilder("naut_");
        imagePath.append(name.toLowerCase().replace(" ", "_").replace("-", "_").replace("ø","o").replace("&","and"));
        return imagePath.toString();
    }

    public int getUnlockedAtLevel() {
        return unlockedAtLevel;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.backstory;
    }

}
