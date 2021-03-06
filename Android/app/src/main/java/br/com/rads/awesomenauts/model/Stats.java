package br.com.rads.awesomenauts.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael_2 on 18/02/14.
 */
public class Stats {

    private int health;
    private int bonusHealth;
    private float movementSpeed;
    private AttackType attackType;
    private List<Role> roles;
    private Mobility mobility;

    public static Stats parseJSON(JSONObject statsJSON) throws JSONException {

        Stats stats = new Stats();
        stats.health = statsJSON.getInt("health");
        stats.bonusHealth = statsJSON.getInt("healthBonus");
        stats.movementSpeed = (float) statsJSON.getDouble("moveSpeed");
        stats.attackType = AttackType.valueOf(statsJSON.getString("attackType").toUpperCase());
        stats.mobility = Mobility.valueOf(statsJSON.getString("mobility").toUpperCase());

        List<Role> roles = new ArrayList<Role>();
        JSONArray roleArray = statsJSON.getJSONArray("role");
        for (int i = 0; i < roleArray.length();i++){
            roles.add(Role.valueOf(roleArray.getString(i).toUpperCase()));
        }
        stats.roles = roles;

        return stats;
    }

    public int getHealth() {
        return health;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Mobility getMobility() {
        return mobility;
    }

    public String getHealthAsString() {
        StringBuilder healthWithBonus = new StringBuilder(String.valueOf(getHealth()));
        healthWithBonus.append(" ");
        healthWithBonus.append("("+sumHealthAndBonus()+")");
        return healthWithBonus.toString();
    }

    private int sumHealthAndBonus() {
        return getBonusHealth()+getHealth();
    }

    public String getRolesAsString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.roles.size(); i++){
            sb.append(this.roles.get(i).getLabel());

            if(i+1 < this.roles.size())
                sb.append(", ");
        }

        return sb.toString();
    }
}
