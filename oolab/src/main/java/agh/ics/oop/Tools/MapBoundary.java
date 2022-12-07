package agh.ics.oop.Tools;


import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Vector2d;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver
{
    /* Ogólnie wystarczy przechowywać tylko Vector2d bo na jedym polu i tak maksymalnie może znaleźć się jedene element*/
    public TreeSet<Vector2d> sortedX = new TreeSet<>((v, u) -> v.x == u.x ? v.y - u.y : v.x - u.x);
    public TreeSet<Vector2d> sortedY = new TreeSet<>( (v, u) -> v.y == u.y ? v.x - u.x : v.y - u.y);
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if ( oldPosition != null )
            removeVector(oldPosition);

        if ( newPosition != null )
            addVector(newPosition);
    }

    private void removeVector ( Vector2d vector ) {
        sortedX.remove(vector);
        sortedY.remove(vector);
    }

    private void addVector ( Vector2d vector ) {
        sortedX.add(vector);
        sortedY.add(vector);
    }

    public Vector2d lowerLeft () {
        return new Vector2d(sortedX.first().x, sortedY.first().y);
    }

    public Vector2d upperRight () {
        return new Vector2d( sortedX.last().x, sortedY.last().y);
    }
}
