package agh.ics.oop;

import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.ISwingEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;

import java.util.ArrayList;

import static java.lang.System.out;


public class World
{
    public static void main(String[] args) {

        // Ogólna opinia kod jest gdzieniegdzie skomplikowany
        // bo jest to połaczenie kilku różnych pomysłów
        // niektóre załozenia z wczesniejszych labolatoriów nie koniecznie dobrze
        // współpracują z nowymi załozeniami

        MoveDirection[] directions;

        try
        {
            directions = new OptionsParser().parse(args);
            //IWorldMap map = new GrassField(10);
            ArrayList arr = new ArrayList<Grass>();
            arr.add(new Grass(new Vector2d(6, 2)));
            arr.add(new Grass(new Vector2d(0, 0)));
            IWorldMap map = new GrassField(arr);
            Vector2d[] positions = { new Vector2d(2,2) };
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
