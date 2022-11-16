package agh.ics.oop;

import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.ISwingEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.tools.OptionsParser;
import agh.ics.oop.tools.SimulationEngine;

import static java.lang.System.out;

// Klasa zawiera notatki porównujące Jave do C#
public class World
{
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        out.println(map);
        engine.run();
        out.println(map);

        //Swing
        MoveDirection[] new_directions = new OptionsParser().parse(args);
        map = new RectangularMap(10, 5);
        Vector2d[] new_positions = { new Vector2d(2,2), new Vector2d(3,4) };
        ISwingEngine new_engine = new SimulationEngine(new_directions, map, new_positions);
        try {
            new_engine.run(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        out.println("Finish");
    }
}
