package agh.ics.oop.Abstracts;

import agh.ics.oop.Animal;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;

public class AbstractWorldmap implements IWorldMap {


    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
