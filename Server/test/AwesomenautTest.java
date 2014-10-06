import models.Awesomenaut;
import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Rafael on 10/5/14.
 */
public class AwesomenautTest extends WithApplication {

    @Before
    public void setup(){
        startPlay();
    }

    @Test
    public void createAndRetrieveAwesomenaut(){

        Awesomenaut awesomenaut = new Awesomenaut();
        awesomenaut.name = "FirstNaut";
        awesomenaut.save();

        Awesomenaut retrieve = Awesomenaut.find.where().eq("name", "FirstNaut").findUnique();
        assertNotNull(retrieve);
        assertEquals("FirstNaut", retrieve.name);

    }

}
