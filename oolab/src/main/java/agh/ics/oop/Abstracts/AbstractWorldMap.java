package agh.ics.oop.Abstracts;

import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;
import agh.ics.oop.tools.MapVisualizer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    //region Fields
    public final Vector2d leftBottom;
    public final Vector2d rightUpper;

    /* Collection is private because nobody should be able to change animals without verification */
    protected List<AbstractWorldMapElement> Elements = new ArrayList<AbstractWorldMapElement>();

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
    public boolean placeElement(AbstractWorldMapElement element) {
        /*We place element only if positions is empty*/
        if(canMoveTo(element.getPosition()))
        {
            Elements.add(element);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(Elements.size()==0)
            return false;

        return Elements.stream().anyMatch(x->x.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position)
    {
        return Elements.stream().filter(x->x.isAt(position)).findAny().orElse(null);
    }

    @Override
    public void removeElement(AbstractWorldMapElement element)
    {
        Elements.remove(element);
    }

    @Override
    public String toString()
    {
        return new MapVisualizer(this).draw(leftBottom, rightUpper);
    }
}
