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

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map;
        IEngine engine;
        ISwingEngine swingEngine;
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        // f b r l f f r r f f f f f f f f

        /* map = new GrassField(10);
        engine = new SimulationEngine(directions, map, positions);
        out.println(map);
        engine.run();
        out.println(map);*/

        //Swing Grass Field
        map = new GrassField(10);
        swingEngine = new SimulationEngine(directions, map, positions);
        try {
            swingEngine.run(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /* Punkt 12 spełniony. */
        out.println("Finish");
    }
}
