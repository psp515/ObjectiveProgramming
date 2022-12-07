import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.RectangularMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangualMapIntegrationTest
{

    @Test
    public void StackTest() {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2), new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4)  };
        Vector2d[] finalPositions = {new Vector2d(2,0), new Vector2d(3,5) };

        boolean exception = false;
        try{
            TestMap(directions,map,positions,finalPositions);
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void MapRangesTest() {
        String[] args = {"r","f","f","f","f","f","f","f","f","f","f","f","f","l","f","f","f","f","f","f","l","f","f","f","f","f","f","f","f","f","f","f","f","l","f","f","f","f","f","f",};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(0,0)};
        Vector2d[] finalPositions = {new Vector2d(0,0) };
        assertTrue(TestMap(directions,map,positions,finalPositions));
    }

    @Test
    public void StackPlaceTest() {
        String[] args = {"f","b","b","f","r","r","l","l","f","f","f","f","l","r","l","r","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,1), new Vector2d(10,10), new Vector2d(10,9)};
        Vector2d[] finalPositions = { new Vector2d(1,0), new Vector2d(1,1), new Vector2d(9,10), new Vector2d(9,9)};
        assertTrue(TestMap(directions,map,positions,finalPositions));
    }

    @Test
    public void OverallTest() {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)  };
        Vector2d[] finalPositions = {new Vector2d(2,0), new Vector2d(3,5) };

        assertTrue(TestMap(directions,map,positions,finalPositions));
    }

    @Test
    public void OverallTest2() {
        String[] args = {"r","r","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","l","l","f","f","f","f","f","f","f","f","f","f","f","f",};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(2,2) };
        Vector2d[] finalPositions = {new Vector2d(10,5), new Vector2d(10,4) };

        assertTrue(TestMap(directions,map,positions,finalPositions));
    }

    private boolean TestMap(MoveDirection[] directions, IWorldMap map, Vector2d[] startingPositions, Vector2d[] finalOccupiedPositions) {
        IEngine engine = new SimulationEngine(directions, map, startingPositions);
        engine.run();
        out.println(map.toString());

        for(Vector2d finishing : finalOccupiedPositions)
            if(!map.isOccupied(finishing))
                return false;

        return true;
    }

}
