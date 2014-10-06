package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafael on 9/24/14.
 */

@Entity
public class Awesomenaut extends Model{

    public static Finder<Long, Awesomenaut> find = new Finder<Long, Awesomenaut>(Long.class, Awesomenaut.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    public String backstory;

    @OneToOne
    public Stats stats;

    @OneToMany
    public List<Skill> skills;

    public List<String> tipsPlayingAs;

    public List<String> tipsPlayingAgainst;

    public String icon;

    public String image;

    public int unlockedAtLevel;

    public Date releaseDate;

    public Awesomenaut(String name, String backstory) {
        this.name = name;
        this.backstory = backstory;
    }

    public static Awesomenaut create(String name, String backstory){
        Awesomenaut newAwesomenaut = new Awesomenaut(name,backstory);
        newAwesomenaut.save();
        return newAwesomenaut;
    }

    public static List<Awesomenaut> all() {
        return find.all();
    }

    public static void create(Awesomenaut awesomenaut){
        awesomenaut.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
