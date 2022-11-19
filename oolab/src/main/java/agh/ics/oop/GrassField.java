package agh.ics.oop;

import agh.ics.oop.Abstracts.AbstractWorldMap;
import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.tools.MapVisualizer;


public class GrassField extends AbstractWorldMap {

    private final int numberOfGrass;

    protected GrassField(int numberOfGrass) {
        super(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        this.numberOfGrass = numberOfGrass;
        GenerateGrass();
    }


    //region privates

    private void GenerateGrass()
    {
        int max_grass = (int) Math.sqrt(10*numberOfGrass);

        int noGrass = 0;

        while (noGrass<numberOfGrass)
        {
            Vector2d v = null;

            if(!this.placeElement(new Grass(Vector2d.randomVector(0, max_grass))))
                noGrass += 1;
        }
    }

    //endregion

    @Override
    public String toString()
    {
        Vector2d min = new Vector2d(0,0);
        Vector2d max = new Vector2d((int)numberOfGrass,(int)numberOfGrass);


        for(AbstractWorldMapElement element : Elements)
        {
            min = min.lowerLeft(element.getPosition());
            max = max.upperRight(element.getPosition());
        }

        return new MapVisualizer(this).draw(min, max);
    }
}
