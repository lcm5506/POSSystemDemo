package com.c57lee.possystemdemo.userinterface.component;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class TextRectangle extends StackPane {

    protected Label textLabel;
    protected Rectangle rect;
    protected boolean dragged = false;

    public TextRectangle(){
        super();
        this.rect = new Rectangle();
        this.textLabel = new Label("");
        this.getChildren().addAll(rect, textLabel);
        setLabelBounds();
    }

    public TextRectangle(String text){
        super();
        this.rect = new Rectangle();
        this.textLabel = new Label(text);
        this.getChildren().addAll(rect, textLabel);
        setLabelBounds();
    }

    public TextRectangle(String text, double x, double y, double width, double height){
        super();
        this.rect = new Rectangle();
        this.textLabel = new Label(text);
        this.getChildren().addAll(rect, textLabel);
        setLabelBounds();
        this.resizeRelocate(x,y,width,height);

    }

    public void setLabelBounds(){
        setColor(Color.TRANSPARENT);
        rect.setStrokeWidth(3);
        rect.setStrokeType(StrokeType.INSIDE);
        rect.setStroke(Color.BLACK);
        textLabel.setFont(new Font("Arial",20));
        textLabel.setBackground(Background.EMPTY);
        rect.widthProperty().bind(this.widthProperty());
        rect.heightProperty().bind(this.heightProperty());
        rect.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragged = false;
            }
        });
        rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragged = true;
            }
        });
        rect.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (!dragged) { // if (event.isDragDetected()) also worked, but idk why it worked.
                    actionOnMouseClicked();
                }
                dragged = false;
            }
        });
    }

    public String getText(){
        return this.textLabel.getText();
    }

    public void setText(String text){
        this.textLabel.setText(text);
    }

    @Override
    public String toString() {
        return "NamedRectangle{" +
                "name='" + getText() + '\'' +
                "x='" + getBoundsInParent().getMinX() + '\'' +
                "y='" + getBoundsInParent().getMinY() + '\'' +
                "width='" + getWidth() + '\'' +
                "height='" + getHeight() + '\'' +
                '}';
    }

    public void setColor(Color color){
        rect.setFill(color);
    }

    public Color getColor(){
        return (Color) rect.getFill();
    }

    public void actionOnMouseClicked() {

    }
}
