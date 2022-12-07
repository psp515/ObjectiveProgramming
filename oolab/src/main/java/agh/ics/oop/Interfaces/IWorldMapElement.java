package agh.ics.oop.Interfaces;

import agh.ics.oop.Vector2d;

public interface IWorldMapElement {

    String getImageString();
    boolean isAt(Vector2d position);
    Vector2d getPosition();
}
