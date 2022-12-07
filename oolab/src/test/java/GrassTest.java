import agh.ics.oop.Grass;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GrassTest {

    @Test
    public void quickTest()
    {
        assertEquals(new Vector2d(2,2), new Grass(new Vector2d(2,2)).getPosition());
    }
    @Test
    public void quickTest1()
    {
        assertEquals(new Vector2d(3,2), new Grass(new Vector2d(3,2)).getPosition());
    }

    @Test
    public void quickTest2()
    {
        assertEquals("*", new Grass(new Vector2d(2,2)).toString());
    }
}
