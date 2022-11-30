package agh.ics.oop.Tools;

import agh.ics.oop.Animal;
import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    //region Fields

    private final MoveDirection[] _directions;
    private final IWorldMap _worldMap;
    private final int _delay;

    public final List<Animal> animals = new ArrayList<Animal>();

    //endregion

    public SimulationEngine(MoveDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition) throws IllegalArgumentException
    {
        this(directions,worldMap,startingAnimalsPosition, 500);
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition, int delay)
            throws IllegalArgumentException
    {
        _directions = directions;
        _worldMap = worldMap;
        _delay = delay;

        for(Vector2d position : startingAnimalsPosition)
        {
            Animal animal = new Animal(position, worldMap);
            animals.add(animal);
        }
    }

    //region Overrides
    @Override
    public void run()
    {
        int i = 0;
        for(MoveDirection direction: _directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
        }
    }

    //endregion

    //region Privates
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("Thread sleep error");
        }
    }

    //endregion
}
