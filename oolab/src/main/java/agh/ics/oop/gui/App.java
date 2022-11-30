package agh.ics.oop.gui;

import agh.ics.oop.Abstracts.AbstractWorldMapElement;
import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.GrassField;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IWorldMap;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    public int windowWidth;
    public int windowHeight;
    public int gridSize = 20;
    GrassField map;
    IEngine engine;

    @Override
    public void init() throws Exception {
        super.init();
        MoveDirection[] directions = new OptionsParser().parse(
                        getParameters().
                                getRaw().
                                toArray(new String[0]));

        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(directions, map, positions);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(drawGrid(map.getMapLeftBottom(), map.getMapRightUpper()), windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.show();

        engine.run();
    }

    public GridPane drawGrid(Vector2d lowerLeft, Vector2d upperRight) {

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        int width = upperRight.x - lowerLeft.x + 1;
        int height = upperRight.y - lowerLeft.y + 1;

        for ( int i = 0; i <= width; i++ )
            grid.getColumnConstraints().add(new ColumnConstraints( gridSize ));

        for ( int i = 0; i <= height; i++ )
            grid.getRowConstraints().add(new RowConstraints( gridSize ));


        Label xy = new Label("x\\y");
        grid.add(xy, 0, 0);

        for ( int i = 1; i <= width; i++ ) {
            Label label = new Label(String.valueOf( lowerLeft.x + i - 1));
            GridPane.setHalignment( label, HPos.CENTER );
            grid.add(label, i, 0);
        }

        for ( int i = 1; i <= height; i++ ) {
            Label label = new Label(String.valueOf( upperRight.y - i + 1));
            GridPane.setHalignment( label, HPos.CENTER );
            grid.add(label, 0, i);
        }


        for ( int i = 1; i <= height; i++ )
        {
            int y = upperRight.y - i + 1;
            for ( int j = 1; j <= width; j++ )
            {
                int x = lowerLeft.x + j - 1;

                Object object = this.map.objectAt(new Vector2d(x, y));
                if ( object != null )
                {
                    Label label = new Label(object.toString());
                    GridPane.setHalignment( label, HPos.CENTER );
                    grid.add(label, j, i, 1, 1);
                }

            }
        }

        this.windowWidth = gridSize * (width + 1);
        this.windowHeight = gridSize * (height + 1);

        return grid;
    }

}
