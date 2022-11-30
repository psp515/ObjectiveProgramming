package agh.ics.oop.Abstracts;

import agh.ics.oop.Animal;
import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;
import agh.ics.oop.Tools.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    //region Fields
    public final Vector2d leftBottom;
    public final Vector2d rightUpper;

    /* Collection is private because nobody should be able to change animals without verification */
    protected Map<Vector2d,AbstractWorldMapElement> Elements = new HashMap<>();

    //endregion

    protected AbstractWorldMap(Vector2d leftBottom, Vector2d rightUpper){
        this.leftBottom = leftBottom;
        this.rightUpper = rightUpper;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return !isOccupied(position) &&
                position.follows(leftBottom) &&
                position.precedes(rightUpper);
    }

    @Override
    public boolean place(AbstractWorldMapElement element) {
        /*We place element only if positions is empty*/
        if(canMoveTo(element.getPosition()))
        {
            if(element instanceof Animal)
            {
                Animal tmp = (Animal) element;
                tmp.addObserver(this);
            }

            Elements.put(element.getPosition(), element);
            return true;
        }

        throw new IllegalArgumentException("Position "+element.getPosition()+" is already occupied.");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(Elements.size()==0)
            return false;

        return Elements.containsKey(position);
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position)
    {
        if(isOccupied(position))
            return Elements.get(position);

        return null;
    }

    @Override
    public void removeElement(AbstractWorldMapElement element)
    {
        Elements.remove(element.getPosition());
    }

    @Override
    public String toString()
    {
        return new MapVisualizer(this).draw(getLeftBottom(), getRightUpper());
    }

    @Override
    public int getElementsSize(){return Elements.size();}

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement element = Elements.get(oldPosition);
        if(element == null)
            return;
        Elements.remove(oldPosition);
        Elements.put(newPosition, element);
    }

    //region Protected

    protected Vector2d getLeftBottom()
    {
        return leftBottom;
    }

    protected Vector2d getRightUpper()
    {
        return rightUpper;
    }

    //endregion
}
