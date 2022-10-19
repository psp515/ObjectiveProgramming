import agh.ics.oop.MapDirection;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;
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
        EnumMap<MapDirection,MapDirection> directionNextAnswers = new EnumMap<MapDirection, MapDirection>(MapDirection.class);
        directionNextAnswers.put(MapDirection.NORTH,MapDirection.EAST);
        directionNextAnswers.put(MapDirection.EAST,MapDirection.SOUTH);
        directionNextAnswers.put(MapDirection.SOUTH,MapDirection.WEST);
        directionNextAnswers.put(MapDirection.WEST,MapDirection.NORTH);
        
        for(MapDirection direction : MapDirection.values())
        {
            assertEquals(directionNextAnswers.get(direction), direction.next());
        }
    }

    @Test
    public void previousTest()
    {
        EnumMap<MapDirection,MapDirection> directionNextAnswers = new EnumMap<MapDirection, MapDirection>(MapDirection.class);
        directionNextAnswers.put(MapDirection.NORTH,MapDirection.WEST);
        directionNextAnswers.put(MapDirection.WEST,MapDirection.SOUTH);
        directionNextAnswers.put(MapDirection.SOUTH,MapDirection.EAST);
        directionNextAnswers.put(MapDirection.EAST,MapDirection.NORTH);

        for(MapDirection direction : MapDirection.values())
        {
            assertEquals(directionNextAnswers.get(direction), direction.previous());
        }
    }

    @Test
    public void toUnitVectorTest()
    {
        EnumMap<MapDirection, Vector2d> directionNextAnswers = new EnumMap<MapDirection, Vector2d>(MapDirection.class);
        directionNextAnswers.put(MapDirection.NORTH, new Vector2d(0,1));
        directionNextAnswers.put(MapDirection.WEST, new Vector2d(-1,0));
        directionNextAnswers.put(MapDirection.SOUTH, new Vector2d(0,-1));
        directionNextAnswers.put(MapDirection.EAST, new Vector2d(1,0));

        for(MapDirection direction : MapDirection.values())
        {
            assertEquals(directionNextAnswers.get(direction), direction.toUnitVector());
        }
    }
}
