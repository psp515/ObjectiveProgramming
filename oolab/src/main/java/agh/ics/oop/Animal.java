package agh.ics.oop;

import org.w3c.dom.ranges.RangeException;

public class Animal
{
    //region Properties

    private MapDirection animalOrientation;
    private Vector2d animalPosition;

    private final Vector2d _mapBottomLeft;
    private final Vector2d _mapTopRight;

    //endregion

    public Animal()
    {
        animalOrientation = MapDirection.NORTH;
        animalPosition = new Vector2d(2,2);
        _mapBottomLeft = new Vector2d(0,0);
        _mapTopRight = new Vector2d(4,4);
    }

    public Animal(Vector2d startingPosition)
    {
        _mapBottomLeft = new Vector2d(0,0);
        _mapTopRight = new Vector2d(4,4);

        if(isValidPosition(startingPosition))
            animalPosition = startingPosition;
        else
            throw new RangeException((short) 0,"Invalid starting position default map size is 5x5 starting in (0,0).");
    }

    //region public

    public boolean isAt(Vector2d position)
    {
        return animalPosition.equals(position);
    }

    public void move(MoveDirection[] directions)
    {
        if(directions == null || directions.length == 0)
            return;

        for(MoveDirection direction : directions)
            move(direction);
    }

    public void move(String[] arguments)
    {
        MoveDirection[] directions = new OptionsParser().parse(arguments);

        if(directions == null || directions.length == 0)
            return;

        for(MoveDirection direction : directions)
            move(direction);
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

    public Vector2d getAnimalPosition()
    {
        return animalPosition;
    }

    //endregion

    //region private

    private void turn(MapDirection direction)
    {
        animalOrientation = direction;
    }

    private void changePosition(Vector2d movement)
    {
        Vector2d tmp = animalPosition.add(movement);

        if(isValidPosition(tmp))
            animalPosition = tmp;
    }

    private boolean isValidPosition(Vector2d newPosition)
    {
        return beetwenRange(_mapBottomLeft.y, _mapTopRight.y, newPosition.y) &&
                beetwenRange(_mapBottomLeft.x,_mapTopRight.x, newPosition.x);
    }

    private boolean beetwenRange(int start, int stop, int position)
    {
        return start <= position && position <= stop;
    }

    //endregion

    //region Overrides

    @Override
    public String toString()
    {
        return String.format("Pozycja: %s, Orientacja: %s", animalPosition.toString(), animalOrientation.toString());
    }

    //endregion
}
