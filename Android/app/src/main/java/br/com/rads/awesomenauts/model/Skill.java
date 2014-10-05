package br.com.rads.awesomenauts.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Skill {

    private static final String TAG = "SKILL";
    private String name;
    private String description;
    private int solar;
    private String image;
    private List<Attribute> attributes;
    private List<Upgrade> upgrades;

    public static List<Skill> parseJSON(JSONArray skillArray) throws JSONException {

        List<Skill> skills = new ArrayList<Skill>();

        for (int i = 0; i < skillArray.length(); i++) {

            JSONObject skillJSON = skillArray.getJSONObject(i);

            Skill skill = new Skill();
            skill.name = skillJSON.getString("name");
            skill.description = skillJSON.getString("description");
            skill.solar = skillJSON.getInt("value");
            skill.attributes = parseAttributes(skillJSON.getJSONArray("attributes"));
            skill.upgrades = parseUpgrades(skillJSON.getJSONArray("upgrades"));

            skills.add(skill);
        }

        return skills;
    }

    private static List<Attribute> parseAttributes(JSONArray attributesArray) throws JSONException {
        return Attribute.parseJSONArray(attributesArray);
    }

    private static List<Upgrade> parseUpgrades(JSONArray upgradesArray) throws JSONException {
        return Upgrade.parserJSONArray(upgradesArray);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSolar() {
        return solar;
    }

    public String getImage() {

        StringBuilder imagePath = new StringBuilder("sk_");
        imagePath.append(name.toLowerCase().replace(" ", "_").replace("/", "_").replace("'", "")
                .replace("-", "_").replace("\"", "").replace(".", "").replace("!", "").replace(":", ""));

        return imagePath.toString();

    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public String getAttributesAsString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < attributes.size(); i++){
            sb.append(attributes.get(i));
            if(i+1 < attributes.size())
                sb.append("\n");
        }

        return sb.toString();
    }

    public void setName(String name) {
        this.name = name;
    }
}
