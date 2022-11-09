package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    //region Fields
    private final int _width;
    private final int _height;

    /* Collection is private because nobody should be able to change animals
    * without veryfication */
    private List<Animal> animals = new ArrayList<Animal>();

    //endregion

    public RectangularMap(int width, int height){
        this._width = width;
        this._height = height;
    }

    //region overrides
    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return !isOccupied(position) &&
                position.follows(new Vector2d(0,0)) &&
                position.precedes(new Vector2d(_width,_height));
    }

    @Override
    public boolean place(Animal animal) {

        /*uses can move because also chceks*/
        if(canMoveTo(animal.getAnimalPosition()))
        {
            animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch(x->x.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream().filter(x->x.isAt(position)).findAny().orElse(null);
    }

    @Override
    public String toString()
    {
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(this._width,this._height));
    }

    //endregion
}
