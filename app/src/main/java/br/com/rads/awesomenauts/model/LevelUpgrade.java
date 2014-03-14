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

    private String attrName;
    private List<String> attrValue;

    public static List<LevelUpgrade> parseJSONArray(JSONArray levelUpgradeArray) throws JSONException {

        List<LevelUpgrade> levels = new ArrayList<LevelUpgrade>();

        for(int i =0; i < levelUpgradeArray.length() ; i++){
            JSONObject levelJSON = levelUpgradeArray.getJSONObject(i);

            LevelUpgrade level = new LevelUpgrade();
            level.attrName = levelJSON.getString("attrName");
            level.attrValue = parseValueArray(levelJSON.getJSONArray("attrValue"));

            levels.add(level);

        }

        return levels;
    }

    private static List<String> parseValueArray(JSONArray attrValue) throws JSONException {

        List<String> values = new ArrayList<String>();

        for (int i = 0; i < attrValue.length(); i++){
            values.add( attrValue.getString(i) );
        }

        return values;
    }

    public String getAttrName() {
        return attrName;
    }

    public List<String> getAttrValue() {
        return attrValue;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(attrName).append(" | ");
        sb.append(attrValue);

        return sb.toString();
    }
}
