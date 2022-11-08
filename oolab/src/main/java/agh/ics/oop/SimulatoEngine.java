package agh.ics.oop;

import java.util.Arrays;

public class SimulatoEngine implements IEngine{

    //region Fields

    private final MapDirection[] _directions;
    private final IWorldMap _worldMap;

    //endregion

    public SimulatoEngine(MapDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition)
    {
        _directions = directions;
        _worldMap = worldMap;

        Animal[] animals = Arrays.stream(startingAnimalsPosition).map()

    }

    //region Overrides
    @Override
    public void run() {
        //todo

    }
    //endregion
}
