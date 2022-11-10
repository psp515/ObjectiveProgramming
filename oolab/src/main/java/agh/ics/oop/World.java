package agh.ics.oop;

import java.util.Arrays;

import static java.lang.System.out;

// Klasa zawiera notatki porównujące Jave do C#
public class World
{
    public static void main(String[] args)
    {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        out.println(map);
        engine.run();
        out.println(map);
        // todo swing -> podmiana map visualizera, albo dodanie drugiego po prostu.
    }

}
