package agh.ics.oop.Interfaces;

import agh.ics.oop.Vector2d;

public interface INotifyObserver {
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
