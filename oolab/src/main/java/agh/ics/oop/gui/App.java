package agh.ics.oop.gui;

import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.GrassField;
import agh.ics.oop.Interfaces.IEngine;
import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import static java.lang.System.out;

public class App extends Application implements IPositionChangeObserver {
    public int windowWidth = 600;
    public int windowHeight = 600;
    public int gridSize = 20;
    private int moveDelay = 300;

    private Scene scene;
    private Stage stage;
    private GridPane grid;
    private GrassField map;
    private IEngine engine;

    public Thread engineThread;

    @Override
    public void init() throws Exception {
        super.init();

        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(map, positions,500, this);
        engineThread = new Thread((Runnable) engine);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button startButton = new Button("Start");
        TextField textField = new TextField();
        TextField delayField = new TextField();

        delayField.setText(String.valueOf(moveDelay));

        textField.setText(String.join(" ", getParameters().getRaw()));

        HBox formBox = new HBox();
        formBox.getChildren().add(textField);
        formBox.getChildren().add(delayField);
        formBox.getChildren().add(startButton);
        formBox.setAlignment(Pos.CENTER);


        Label errorMessage = new Label("");
        errorMessage.setPadding(new Insets(10));
        errorMessage.setTextFill(Color.color(1,0,0));
        HBox errorMessageBox = new HBox();
        errorMessageBox.getChildren().add(errorMessage);
        errorMessageBox.setAlignment(Pos.CENTER);

        HBox gridContainer = new HBox();
        this.grid = new GridPane();
        gridContainer.getChildren().add(this.grid);
        gridContainer.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().add(formBox);
        vBox.getChildren().add(errorMessageBox);
        vBox.getChildren().add(gridContainer);

        this.scene = new Scene(vBox);


        try {

            Platform.runLater(() -> {
                try
                {
                    drawGrid(grid,map.getMapLeftBottom(),map.getMapRightUpper());
                }
                catch (FileNotFoundException e) { throw new RuntimeException(e); }
            });


            primaryStage.setScene(this.scene);
            primaryStage.show();

            this.stage = primaryStage;
            this.stage.setMaximized(true);


            startButton.setOnAction( (e) -> {
                if ( !engineThread.isAlive() ) {
                    errorMessage.setText("");

                    try {
                        this.moveDelay = Integer.parseInt( delayField.getText() );
                        this.engineThread = new Thread((Runnable) this.engine);
                        this.engine.setDirections(new OptionsParser().parse(textField.getText().trim().split("\\s+")));

                        this.engineThread.start();

                    } catch (NumberFormatException ex){
                        errorMessage.setText("Error: \""+ delayField.getText() + "\" is not an integer");
                    } catch (IllegalArgumentException ex) {
                        errorMessage.setText("Error: "+ ex.getMessage());
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            out.println("\nIllegal argument: " + e.getMessage() + "\n" );
            primaryStage.close();
            Platform.exit();
        }


    }

    public void drawGrid(GridPane grid, Vector2d lowerLeft, Vector2d upperRight) throws FileNotFoundException {

        grid.getChildren().clear();

        int width = upperRight.x - lowerLeft.x + 1;
        int height = upperRight.y - lowerLeft.y + 1;

        for ( int i = 0; i <= width; i++ )
            grid.getColumnConstraints().add(new ColumnConstraints(  ));

        for ( int i = 0; i <= height; i++ )
            grid.getRowConstraints().add(new RowConstraints(  ));


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

                GuiElementBox guiElementBox = new GuiElementBox(this.map.objectAt(new Vector2d(x, y)));
                grid.add(guiElementBox.container, j, i,1,1);
            }
        }


        grid.setGridLinesVisible(true);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("Thread sleep error");
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        sleep(moveDelay);
        Platform.runLater(() -> {
            try
            {
                drawGrid(grid,map.getMapLeftBottom(),map.getMapRightUpper());
            }
            catch (FileNotFoundException e) { throw new RuntimeException(e); }
        });
    }
}
