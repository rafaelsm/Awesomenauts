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
        stats.setHealth(statsJSON.getInt("health"));
        stats.setBonusHealth(statsJSON.getInt("healthBonus"));
        stats.setMovementSpeed((float) statsJSON.getDouble("moveSpeed"));
        stats.setAttackType(AttackType.valueOf(statsJSON.getString("attackType").toUpperCase()));
        stats.setMobility(Mobility.valueOf(statsJSON.getString("mobility").toUpperCase()));

        List<Role> roles = new ArrayList<Role>();
        JSONArray roleArray = statsJSON.getJSONArray("role");
        for (int i = 0; i < roleArray.length();i++){
            roles.add(Role.valueOf(roleArray.getString(i).toUpperCase()));
        }
        stats.setRoles(roles);

        return stats;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Mobility getMobility() {
        return mobility;
    }

    public void setMobility(Mobility mobility) {
        this.mobility = mobility;
    }

}
