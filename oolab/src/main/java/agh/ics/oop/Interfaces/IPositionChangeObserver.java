package agh.ics.oop.Interfaces;

import agh.ics.oop.Vector2d;


public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
