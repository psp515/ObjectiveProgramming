import agh.ics.oop.MapDirection;
import agh.ics.oop.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest
{
    @Test
    public void nextTest()
    {

        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());

    }
}
