package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMap;
import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.Tools.MapBoundary;

import java.util.ArrayList;

import static java.lang.System.out;


public class GrassField extends AbstractWorldMap {

    public final int numberOfGrass;
    private final int lastGroundForGrass;
    public final MapBoundary boundary;

    public GrassField(int numberOfGrass) {
        super(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        this.numberOfGrass = numberOfGrass;
        lastGroundForGrass = (int) Math.sqrt(10*numberOfGrass);
        boundary = new MapBoundary();
        GrowGrass(numberOfGrass);

    }

    public GrassField(ArrayList<Grass> array) {
        //Tylko dla testów
        super(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        this.numberOfGrass = array.size();
        lastGroundForGrass = (int) Math.sqrt(10*numberOfGrass);
        boundary = new MapBoundary();

        for(Grass grass :array)
            this.place(grass);
    }


    //region Privates

    //endregion

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);
        boundary.positionChanged(oldPosition, newPosition);
    }

    @Override
    public boolean place(AbstractWorldMapElement element)
    {
        if(!element.getPosition().follows(this.leftBottom) || !element.getPosition().precedes(this.rightUpper))
            throw new IllegalArgumentException("Position "+element.getPosition()+" is already occupied.");

        if(isOccupied(element.getPosition()))
        {
            AbstractWorldMapElement elementOnPosition = this.objectAt(element.getPosition());

            if(elementOnPosition instanceof Grass && element instanceof Animal)
            {
                //Nie ma wpływu na boundary bo trawa znika i pojawia sie w jej miejsce zwierzak
                this.removeElement(elementOnPosition);
                Elements.put(element.getPosition(),element);
                Animal tmp = (Animal) element;
                tmp.addObserver(this);
                GrowGrass(1);
                return true;
            }

            throw new IllegalArgumentException("Position "+element.getPosition()+" is already occupied.");
        }

        Elements.put(element.getPosition(),element);
        boundary.positionChanged(null, element.getPosition());

        if(element instanceof Animal)
        {
            Animal tmp = (Animal) element;
            tmp.addObserver(this);
        }

        return true;
    }

    @Override
    public Vector2d getMapLeftBottom(){
        return boundary.lowerLeft();
    }

    @Override
    public Vector2d getMapRightUpper(){
        return boundary.upperRight();
    }

    public void GrowGrass(int numberOfNewGrass) {
        int counter = 0;
        int total_count = 0;
        while (counter < numberOfNewGrass)
        {
            Vector2d newPos = Vector2d.randomVector(0, this.lastGroundForGrass);

            if(!this.isOccupied(newPos))
            {
                this.place(new Grass(newPos));
                boundary.positionChanged(null, newPos);
                counter += 1;
            }

            total_count += 1;

            if (total_count/numberOfNewGrass > 100) {
                break;
            }
        }

        if(counter < numberOfNewGrass)
        {
            for(int i = 0; i < lastGroundForGrass;i++)
                for(int j = 0; j < lastGroundForGrass; j++)
                {
                    Vector2d newPos = Vector2d.randomVector(0, this.lastGroundForGrass);

                    if(!this.isOccupied(newPos))
                    {
                        this.place(new Grass(newPos));
                        counter += 1;
                    }

                    if (counter == numberOfGrass)
                        return;
                }
        }

        if(counter < numberOfNewGrass)
            out.println("Couldn't generate grass.");
    }
}
