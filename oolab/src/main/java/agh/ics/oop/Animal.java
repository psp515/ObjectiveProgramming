package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.Enums.MapDirection;
import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;

public class Animal extends AbstractWorldMapElement
{
    //region Properties
    private MapDirection animalOrientation;
    public final IWorldMap _worldMap;

    //endregion

    public Animal(Vector2d initialPosition, IWorldMap worldMap) {
        super(initialPosition);
        _worldMap = worldMap;
        animalOrientation = MapDirection.NORTH;

        if(!worldMap.placeElement(this))
            throw new IllegalArgumentException(String.format("Postion %s is already occupied.", initialPosition.toString()));
    }

    //region public

    public void move(MoveDirection[] directions) {
        if(directions == null || directions.length == 0)
            return;

        for(MoveDirection direction : directions)
            move(direction);
    }
    public void move(String[] arguments) {
        MoveDirection[] directions = new OptionsParser().parse(arguments);

        if(directions == null || directions.length == 0)
            return;

        move(directions);
    }
    public void move(MoveDirection direction)
    {
        if(direction == MoveDirection.LEFT)
            turn(animalOrientation.previous());
        else if(direction == MoveDirection.RIGHT)
            turn(animalOrientation.next());
        else if(direction == MoveDirection.FORWARD)
            changePosition(animalOrientation.toUnitVector());
        else
            changePosition(animalOrientation.next().next().toUnitVector());
    }

    //endregion

    //region private

    private void turn(MapDirection direction)
    {
        animalOrientation = direction;
    }

    private void changePosition(Vector2d movement) {
        Vector2d tmp = position.add(movement);

        if(!_worldMap.canMoveTo(tmp))
        {
            AbstractWorldMapElement element = (AbstractWorldMapElement) _worldMap.objectAt(tmp);
            if(element instanceof Grass)
            {
                GrassField map = (GrassField) _worldMap;
                map.removeElement(element);
                position = tmp;
                map.GrowGrass(1);
            }
            return;
        }

        position = tmp;
    }

    //endregion

    //region Overrides

    @Override
    public String toString()
    {
        return String.format("%s", animalOrientation.toString());
    }

    //endregion

}
