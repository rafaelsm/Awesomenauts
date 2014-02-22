package br.com.rads.awesomenauts.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Upgrade {

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
            //TODO: atribuir elementos do json
        }

        return upgrades;
    }
}
