package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
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

    public String description;

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
