package agh.ics.oop.Tools;

import agh.ics.oop.Animal;
import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.ISwingEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine, ISwingEngine {

    //region Fields

    private final MoveDirection[] _directions;
    private final IWorldMap _worldMap;

    public final List<Animal> animals = new ArrayList<Animal>();

    //endregion

    public SimulationEngine(MoveDirection[] directions, IWorldMap worldMap, Vector2d[] startingAnimalsPosition) {
        _directions = directions;
        _worldMap = worldMap;

        for(Vector2d position : startingAnimalsPosition) {

            try {

                /* creating animal and specyfing map automatically asigns animal to map or
                * throws exception when position is already taken
                *  */
                Animal animal = new Animal(position, worldMap);
                animals.add(animal);
            }
            catch (IllegalArgumentException e) {
                out.printf("%s Animal not placed.%n",e);
            }
        }
    }

    //region Overrides
    @Override
    public void run()
    {
        int i = 0;
        for(MoveDirection direction: _directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
            //out.println(_worldMap.toString());
        }
    }

    @Override
    public void run(int frameDelay)  {

        //Setup
        if(frameDelay < 100)
            frameDelay = 100;

        JLabel map = new JLabel("Loading...", SwingConstants.CENTER);
        Visualize(map);

        try {
            Thread.sleep(frameDelay);
            map.setText(HTMLWorldMap("Position: --","Action: --"));
            Thread.sleep(frameDelay);
        } catch (InterruptedException e) {
            out.println("Error");
        }

        //Start Simulation
        int i = 0;
        for(MoveDirection direction: _directions)
        {
            Animal animated_animal = animals.get(i%animals.size());
            animated_animal.move(direction);
            map.setText(HTMLWorldMap("Position: " + animated_animal.getPosition().toString() + " " + animated_animal,"Action: " + direction));

            try {
                Thread.sleep(frameDelay);
            }
            catch (InterruptedException e) {
                out.println("Error");
            }
            finally {
                i+=1;
            }
        }
    }
    //endregion

    //region Privates

    private String HTMLWorldMap(String pos, String act)
    {
        return "<html>" + _worldMap.toString().replaceAll("\n", "<br/>") +
                "<br/>" + act +
                "<br/>" + pos + "</html>";
    }

    private void Visualize(JLabel map)
    {
        JFrame frame = new JFrame("Map Animation");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(map);
        frame.add(panel);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //endregion

}
