package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine{

    //region Fields

    private final MoveDirection[] _directions;
    private final IWorldMap _worldMap;

    private List<Animal> animals = new ArrayList<Animal>();

    //endregion

    public SimulationEngine(MoveDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition)
    {
        _directions = directions;
        _worldMap = worldMap;

        for(Vector2d position : startingAnimalsPosition) {

            try {

                /* creating animal and specyfing map automatically asigns animal to map or
                * throws exception when position is already taken
                *  */
                Animal animal = new Animal(worldMap, position);
                animals.add(animal);
            }
            catch (IllegalArgumentException e) {
                out.printf("%s Animal not placed.%n",e);
            }
        }
    }

    //region Overrides
    @Override
    public void run()
    {
        int i = 0;
        out.println(_worldMap.toString());
        for(MoveDirection direction: _directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
            out.println(_worldMap.toString());
        }
    }
    //endregion
}
