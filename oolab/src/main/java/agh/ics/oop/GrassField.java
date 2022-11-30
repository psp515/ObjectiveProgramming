package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMap;
import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.Tools.MapVisualizer;

import static java.lang.System.out;


public class GrassField extends AbstractWorldMap {

    public final int numberOfGrass;
    private final int lastGroundForGrass;

    public GrassField(int numberOfGrass) {
        super(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        this.numberOfGrass = numberOfGrass;
        lastGroundForGrass = (int) Math.sqrt(10*numberOfGrass);
        GrowGrass(numberOfGrass);
        /*Zakładam że grass map działa podobnie jak mapa w minecrafcie
        tzn nie da sie za nia wyjsc tylko przenosi nas na wspołrzedne po drugiej stronie mapy*/
    }


    //region Privates

    //endregion

    @Override
    public boolean placeElement(AbstractWorldMapElement element)
    {
        if(!element.getPosition().follows(this.leftBottom) || !element.getPosition().precedes(this.rightUpper))
            return false;

        if(isOccupied(element.getPosition()))
        {
            AbstractWorldMapElement elementOnPosition = this.objectAt(element.getPosition());

            if(elementOnPosition instanceof Grass && element instanceof Animal)
            {
                this.removeElement(elementOnPosition);
                Elements.add(element);
                GrowGrass(1);
                return true;
            }

            return false;
        }

        Elements.add(element);
        return true;
    }

    @Override
    protected Vector2d getLeftBottom(){
        Vector2d min = new Vector2d(0,0);

        for(AbstractWorldMapElement element : Elements)
            min = min.lowerLeft(element.getPosition());

        return min;
    }

    @Override
    protected Vector2d getRightUpper(){
        Vector2d max = new Vector2d(0,0);

        for(AbstractWorldMapElement element : Elements)
            max = max.upperRight(element.getPosition());

        return max;
    }

    public void GrowGrass(int numberOfNewGrass)
    {
        int counter = 0;
        int total_count = 0;
        while (counter < numberOfNewGrass)
        {
            if(this.placeElement(new Grass(Vector2d.randomVector(0, this.lastGroundForGrass))))
                counter += 1;


            total_count += 1;

            if (total_count/numberOfNewGrass > 100) {
                break;
            }
        }

        if(counter < numberOfNewGrass)
        {
            for(int i = 0; i < lastGroundForGrass;i++)
                for(int j = 0; j < lastGroundForGrass; j ++)
                {
                    if(this.placeElement(new Grass(new Vector2d(i, j))))
                        counter += 1;
                    if (counter == numberOfGrass)
                        return;
                }
        }

        if(counter<numberOfNewGrass)
            out.println("Couldn't generate grass.");
    }
}
