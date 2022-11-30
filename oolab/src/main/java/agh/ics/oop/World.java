package agh.ics.oop;

import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Interfaces.ISwingEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;

import static java.lang.System.out;


public class World
{
    public static void main(String[] args) {

        Application.launch(App.class, args);


        out.println("Finish");
    }
}
