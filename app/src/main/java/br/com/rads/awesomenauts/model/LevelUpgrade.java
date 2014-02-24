package br.com.rads.awesomenauts.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class LevelUpgrade {

    private int level;
    private String attrName;
    private float attrValue;
    private Format format;

    public static List<LevelUpgrade> parseJSONArray(JSONArray levelUpgradeArray) throws JSONException {

        List<LevelUpgrade> levels = new ArrayList<LevelUpgrade>();

        for(int i =0; i < levelUpgradeArray.length() ; i++){
            JSONObject levelJSON = levelUpgradeArray.getJSONObject(i);

            LevelUpgrade level = new LevelUpgrade();
            level.level = levelJSON.getInt("level");
            level.attrName = levelJSON.getString("attrName");
            level.attrValue = (float) levelJSON.getDouble("attrValue");
            level.format = Format.valueOf( levelJSON.getString("format").toUpperCase() );

        }

        return levels;
    }

    public int getLevel() {
        return level;
    }

    public String getAttrName() {
        return attrName;
    }

    public float getAttrValue() {
        return attrValue;
    }

    public Format getFormat() {
        return format;
    }
}
