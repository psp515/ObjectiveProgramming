package agh.ics.oop.Interfaces;


/**
 * The interface responsible for managing the moves of the animals with use of swing.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author psp515
 *
 */
public interface ISwingEngine {
    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     * Each move is displayed in frame.
     * @param frameDelay Delay every each frame
     */
    void run(int frameDelay) throws InterruptedException;
}
