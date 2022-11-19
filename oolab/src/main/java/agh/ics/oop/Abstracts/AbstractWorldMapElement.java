package agh.ics.oop.Abstracts;

import agh.ics.oop.Vector2d;

public class AbstractWorldMapElement {

    protected Vector2d position;

    public AbstractWorldMapElement(Vector2d position) {
        this.position = position;
    }

    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }

    public Vector2d getPosition()
    {
        return position;
    }

}
