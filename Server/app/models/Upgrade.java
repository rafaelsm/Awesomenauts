package models;

import javax.persistence.Id;

/**
 * Created by Rafael on 10/5/14.
 */
public class Upgrade {

    @Id
    public Long id;

    public String name;
    public String value;

}
