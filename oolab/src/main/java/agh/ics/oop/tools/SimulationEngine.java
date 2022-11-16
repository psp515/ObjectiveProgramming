package agh.ics.oop.tools;

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
                Animal animal = new Animal(worldMap, position);
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
        out.println(_worldMap.toString());
        for(MoveDirection direction: _directions)
        {
            animals.get(i%animals.size()).move(direction);
            i+=1;
        }
    }

    @Override
    public void run(int frameDelay)  {

        //Setup
        if(frameDelay < 10)
            frameDelay = 10;

        JLabel map = new JLabel("Loading...", SwingConstants.CENTER);
        JLabel position = new JLabel("", SwingConstants.CENTER);
        JLabel action = new JLabel("", SwingConstants.CENTER);
        Visualize(map, position, action);

        try {
            Thread.sleep(frameDelay);
            map.setText(HTMLWorldMap());
            position.setText("Position: --");
            action.setText("Action: --");
            Thread.sleep(frameDelay);
        } catch (InterruptedException e) {
            out.println("Error");
        }

        //Start Simulation
        int i = 0;
        for(MoveDirection direction: _directions)
        {
            Animal animated_animal = animals.get(i%animals.size());
            position.setText("Position: " + animated_animal.getAnimalPosition().toString() + " " + animated_animal);
            action.setText("Action: " + direction);
            animated_animal.move(direction);
            map.setText(HTMLWorldMap());

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

    private String HTMLWorldMap()
    {
        return "<html>" + _worldMap.toString().replaceAll("\n", "<br/>") + "</html>";
    }

    private void Visualize(JLabel map, JLabel position, JLabel action)
    {
        JFrame frame = new JFrame("Map Animation");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(map);
        panel.add(position);
        panel.add(action);
        frame.add(panel);
        frame.setSize(256,256);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //endregion

}
