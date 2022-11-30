package agh.ics.oop.Interfaces;

import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.Vector2d;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2d position);

    /**
     * Place a animal on the map.
     *
     * @param element
     *            The element to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(AbstractWorldMapElement element);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    AbstractWorldMapElement objectAt(Vector2d position);

    /**
     * Removes element at given position.
     *
     * @param element
     *            Element of the map.
     */
    void removeElement(AbstractWorldMapElement element);

    /**
    * @return Number of elements on map.
    * */
    int getElementsSize();

    Vector2d getMapLeftBottom();

    Vector2d getMapRightUpper();
}
