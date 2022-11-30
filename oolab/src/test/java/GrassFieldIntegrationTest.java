import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.GrassField;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldIntegrationTest {

    @Test
    public void GrassTest() {

        String[] args = {};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(120);
        Vector2d[] positions = { };
        Vector2d[] finalPositions = { };

        assertTrue(TestMap(directions,map,positions,finalPositions,120));
    }

    @Test
    public void StackTest() {
        String[] args = {"f","b"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2), new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4) ,new Vector2d(2,2), new Vector2d(3,4)  };
        Vector2d[] finalPositions = {new Vector2d(2,3), new Vector2d(3,3) };

        boolean exception = false;
        try
        {
            TestMap(directions,map,positions,finalPositions,12);
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void StackPlaceTest() {
        String[] args = {"f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(9);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,1), new Vector2d(1,0), new Vector2d(1,1)};
        Vector2d[] finalPositions = { new Vector2d(0,0), new Vector2d(0,2), new Vector2d(1,0), new Vector2d(1,2)};
        assertTrue(TestMap(directions, map, positions,finalPositions,13));
    }

    @Test
    public void OverallTest() {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        Vector2d[] finalPositions = {new Vector2d(2,-1), new Vector2d(3,7) };

        assertTrue(TestMap(directions,map,positions,finalPositions,12));
    }

    @Test
    public void OverallTest2() {
        String[] args = {"f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b","b"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(40);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(2,2) };
        Vector2d[] finalPositions = {new Vector2d(0,1), new Vector2d(2,3) };

        assertTrue(TestMap(directions,map,positions,finalPositions,42));
    }

    private boolean TestMap(MoveDirection[] directions, IWorldMap map, Vector2d[] startingPositions, Vector2d[] finalOccupiedPositions, int final_elements) {
        IEngine engine = new SimulationEngine(directions, map, startingPositions);
        engine.run();
        out.println(map.toString());

        for(Vector2d finishing : finalOccupiedPositions)
            if(!map.isOccupied(finishing))
                return false;

        return map.getElementsSize() == final_elements;
    }

}
