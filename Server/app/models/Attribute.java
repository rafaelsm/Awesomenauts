package models;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Rafael on 10/5/14.
 */
public class Attribute {

    @Id
    public Long id;
    
    public String name;
    public String description;
    public int solar;
    public String flavor;
    public String image;

    @OneToMany
    public List<LevelUpgrade> levelUpgrades;

}
