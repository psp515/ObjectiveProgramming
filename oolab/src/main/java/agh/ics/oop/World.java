package agh.ics.oop;

import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.ISwingEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;

import static java.lang.System.out;


public class World
{
    public static void main(String[] args) {

        MoveDirection[] directions;

        try
        {
            directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(20);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            ISwingEngine swingEngine = new SimulationEngine(directions, map, positions);
            swingEngine.run(1000);
        }
        catch (InterruptedException | IllegalArgumentException e)
        {
            out.println(e.getMessage());
        }

        out.println("Finish");
    }
}
