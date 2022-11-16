package agh.ics.oop;

import agh.ics.oop.Interfaces.IWorldMap;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class GrassField implements IWorldMap {

    public GrassField(int numOfGrass)
    {
        GenerateGrass();

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isOccupied(position) &&
                position.follows(new Vector2d(MIN_VALUE,MIN_VALUE)) &&
                position.follows(new Vector2d(MAX_VALUE,MAX_VALUE)) ;
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

    @Override
    public String toString(){
        return "";
    }


    //region privates

    private void GenerateGrass()
    {
        //TODO
    }

    //endregion

}
