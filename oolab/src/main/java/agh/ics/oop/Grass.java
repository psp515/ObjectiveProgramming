package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMapElement;

public class Grass extends AbstractWorldMapElement {

    @Override
    public String getImageString()
    {
        return RESOURCES_STRING + "grass.png";
    }

    public Grass(Vector2d position)
    {
        super(position);
    }
    @Override
    public String toString()
    {
        return "Grass";
    }
}
