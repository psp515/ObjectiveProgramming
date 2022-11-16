package agh.ics.oop;

public class Grass {

    private final Vector2d _position;

    public Grass(Vector2d position){
        this._position = position;
    }

    public Vector2d getVector()
    {
        return _position;
    }

    @Override
    public String toString()
    {
        return "*";
    }

}
