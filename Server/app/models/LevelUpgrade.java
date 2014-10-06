package models;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Rafael on 10/5/14.
 */
public class LevelUpgrade {

    @Id
    public Long id;

    public String attrName;
    public List<String> attrValue;

}
