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
public class Upgrade {

    private static final String TAG = "UPGRADE";
    private String name;
    private String description;
    private int solar;
    private String flavor;
    private String image;
    private List<LevelUpgrade> levelUpgrades;

    public static List<Upgrade> parserJSONArray(JSONArray upgradesArray) throws JSONException {

        List<Upgrade> upgrades = new ArrayList<Upgrade>();

        for (int i = 0; i < upgradesArray.length(); i++){
            JSONObject upgradeJSON = upgradesArray.getJSONObject(i);

            Upgrade upgrade = new Upgrade();
            upgrade.name = upgradeJSON.getString("name");
            upgrade.description = upgradeJSON.getString("description");
            upgrade.solar = upgradeJSON.getInt("value");
            upgrade.flavor = upgradeJSON.getString("flavor");
            upgrade.levelUpgrades = parseLevelUpgrade(upgradeJSON.getJSONArray("levelUpgrade"));

            upgrades.add(upgrade);
        }

        return upgrades;
    }

    private static List<LevelUpgrade> parseLevelUpgrade(JSONArray levelUpgradeArray) throws JSONException {
        return  LevelUpgrade.parseJSONArray(levelUpgradeArray);
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

    public String getFlavor() {
        return flavor;
    }

    public String getImage() {

        StringBuilder imagePath = new StringBuilder("up_");
        imagePath.append(name.toLowerCase()
                .replace(" ", "_").replace("/","_").replace("'","")
                .replace("-","_").replace("\"","").replace(".","").replace("!","").replace(":", ""));
        
        return imagePath.toString();

    }

    public List<LevelUpgrade> getLevelUpgrades() {
        return levelUpgrades;
    }
}
