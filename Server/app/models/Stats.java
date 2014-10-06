package models;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Rafael on 10/5/14.
 */
public class Stats {

    @Id
    public long id;

    public int health;
    public int bonusHealth;
    public float movementSpeed;

    public AttackType attackType;

    @ManyToMany
    public List<Role> roles;

    public Mobility mobility;

}
