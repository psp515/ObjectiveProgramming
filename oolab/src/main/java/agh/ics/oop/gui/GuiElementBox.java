package agh.ics.oop.gui;

import agh.ics.oop.Interfaces.IWorldMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private final ImageView view;
    private final Label label;

    public VBox container;

    GuiElementBox(IWorldMapElement element
    ) throws FileNotFoundException {
        String url = element.getImageString();

        this.image = url.length() > 0 ? new Image( new FileInputStream(url)) : null;
        this.view = new ImageView(this.image);

        this.view.setFitHeight(20);
        this.view.setFitWidth(20);

        this.label = new Label(element.toString() );

        this.container = new VBox();

        this.container.getChildren().add(this.view);
        this.container.getChildren().add(this.label);

        this.container.setAlignment(Pos.CENTER);
    }

    void updateElement(IWorldMapElement element ) {
        if (element == null) {
            this.image = null;
            this.view.setImage(this.image);
            this.label.setText("");
        } else {
            this.image = new Image(element.getImageString());
            this.view.setImage(this.image);
            this.label.setText(element.getPosition().toString());
        }
    }
}
