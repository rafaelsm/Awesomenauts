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
    private String attrValue;

    public static List<LevelUpgrade> parseJSONArray(JSONArray levelUpgradeArray) throws JSONException {

        List<LevelUpgrade> levels = new ArrayList<LevelUpgrade>();

        for(int i =0; i < levelUpgradeArray.length() ; i++){
            JSONObject levelJSON = levelUpgradeArray.getJSONObject(i);

            LevelUpgrade level = new LevelUpgrade();
            level.level = levelJSON.getInt("level");
            level.attrName = levelJSON.getString("attrName");
            level.attrValue = levelJSON.getString("attrValue");

        }

        return levels;
    }

    public int getLevel() {
        return level;
    }

    public String getAttrName() {
        return attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

}
