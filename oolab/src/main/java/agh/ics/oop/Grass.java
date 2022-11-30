package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMapElement;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d position)
    {
        super(position);
    }
    @Override
    public String toString()
    {
        return "*";
    }
}
