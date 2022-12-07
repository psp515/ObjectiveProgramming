package agh.ics.oop.Tools;

import agh.ics.oop.Animal;
import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    //region Fields

    private MoveDirection[] _directions;
    private final IWorldMap _worldMap;
    private final int _delay;

    public final List<Animal> animals = new ArrayList<Animal>();

    //endregion

    public SimulationEngine(MoveDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition, int delay, IPositionChangeObserver app)
            throws IllegalArgumentException
    {
        _directions = directions;
        _worldMap = worldMap;
        _delay = delay;

        for(Vector2d position : startingAnimalsPosition)
        {
            Animal animal = new Animal(position, worldMap);
            animals.add(animal);
            animal.addObserver(app);
        }
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

    public SimulationEngine(IWorldMap worldMap, Vector2d[] startingAnimalsPosition, int delay, IPositionChangeObserver app) throws IllegalArgumentException
    {
        _worldMap = worldMap;
        _delay = delay;

        for(Vector2d position : startingAnimalsPosition)
        {
            Animal animal = new Animal(position, worldMap);
            animals.add(animal);
            animal.addObserver(app);
        }
    }


    public void setDirections(MoveDirection[] directions)
    {
        this._directions = directions;
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

}
