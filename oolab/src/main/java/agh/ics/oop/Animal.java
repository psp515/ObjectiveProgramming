package agh.ics.oop;

import org.w3c.dom.ranges.RangeException;

public class Animal
{
    //region Properties
    private MapDirection animalOrientation;
    private Vector2d animalPosition;
    private IWorldMap worldMap;

    //endregion

    private Animal()
    {
        /* Można wykorzystać ten konstruktor do inicjalizacji pól które są zawsze
         * takie same na począku dla każdego obiektu tej klasy.
         */
        this.animalOrientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap worldMap) {
        this();
        this.worldMap = worldMap;
        this.animalPosition = new Vector2d(0,0);

        if(!worldMap.place(this))
            throw new IllegalArgumentException(String.format("Postion %s is already occupied.", this.animalPosition.toString()));
    }
    public Animal(IWorldMap worldMap, Vector2d initialPosition) {
        this();
        this.worldMap = worldMap;
        this.animalPosition = initialPosition;

        if(!worldMap.place(this))
            throw new IllegalArgumentException(String.format("Postion %s is already occupied.",initialPosition.toString()));
    }

    //region public

    public boolean isAt(Vector2d position)
    {
        return animalPosition.equals(position);
    }

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

    private void changePosition(Vector2d movement) {
        Vector2d tmp = animalPosition.add(movement);

        if(worldMap.canMoveTo(tmp))
            animalPosition = tmp;
    }

    //endregion

    //region Overrides

    @Override
    public String toString()
    {
        return String.format("%s", animalOrientation.toStringShort());
    }

    //endregion

}
