package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMap;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        super(new Vector2d(0,0), new Vector2d(width, height));
    }

}
