package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.controller.LocationSettingController;
import com.c57lee.possystemdemo.obj.Location;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LocationSettingUI {
    Stage stage;
    Scene scene;
    Group root;
    Pane pane;
    LocationSettingController controller;
    TextField nameTextField,xTextField,yTextField,widthTextField,heightTextField;
    Button newButton, saveButton, removeButton, resetButton;

    public LocationSettingUI(Stage stage){
        this.stage = stage;
        root = new Group();
        scene = new Scene(root);
        controller = new LocationSettingController();
        stage.setScene(scene);
        initialize();
        populate();
        stage.show();
    }

    public void initialize(){
        stage.setTitle("Location Setting");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }
        });

        Label label = new Label("Location Setting");
        label.setFont(new Font("Arial",30));


        newButton = new Button("New");
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("New Location");
                nameDialog.setResizable(false);
                nameDialog.setHeaderText("New Location Name");
                nameDialog.setContentText("Enter a name for new location.");
                String newName = nameDialog.showAndWait().orElse("");
                if (!newName.isBlank()){
                    LocationSettingTextRectangle rect = createRectangle();
                    rect.setText(newName);
                    rect.relocate(pane.getWidth()/2,pane.getHeight()/2);
                    rect.setPrefSize(100,100);
                    pane.getChildren().add(rect);
                    NodeDragResizer.makeDragResizable(rect);
                }
            }
        });

        saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Node node: pane.getChildren()){
                    if (node instanceof LocationSettingTextRectangle){
                        Location l = ((LocationSettingTextRectangle) node).asLocation();
                        if (controller.contains(l))
                            controller.updateLocation(l);
                        else
                            controller.addLocation(l);
                    }
                }
            }
        });

        removeButton = new Button("Remove");
        removeButton.setDisable(true);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeSelected();
            }
        });

        resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearSelected();
            }
        });

        HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(2.0);
        buttonHBox.getChildren().addAll(newButton,saveButton,removeButton,resetButton);


        pane = new Pane();
        pane.setPrefSize(800,800);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,BorderStroke.MEDIUM)));
//
//        Rectangle rect = new Rectangle(5,5,100,100);
//        rect.setFill(Color.RED);
//        TextRectangle textRectangle = new TextRectangle("Fire",200,200,100,100);
//        pane.getChildren().addAll(rect, textRectangle);
//        NodeDragResizer.makeDragResizable(rect);
//        NodeDragResizer.makeDragResizable(textRectangle);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2.0);
        gridPane.setVgap(2.0);

        Label nameLabel = new Label("Name");
        gridPane.add(nameLabel,0,0);
        nameTextField = new TextField();
        nameTextField.setMaxWidth(100);
        gridPane.add(nameTextField,0,1);

        Label xLabel = new Label("X");
        gridPane.add(xLabel,1,0);
        xTextField = new TextField();
        xTextField.setMaxWidth(100);
        gridPane.add(xTextField,1,1);

        Label yLabel = new Label("Y");
        gridPane.add(yLabel,2,0);
        yTextField = new TextField();
        yTextField.setMaxWidth(100);
        gridPane.add(yTextField,2,1);

        Label widthLabel = new Label("Width");
        gridPane.add(widthLabel,3,0);
        widthTextField = new TextField();
        widthTextField.setMaxWidth(100);
        gridPane.add(widthTextField,3,1);

        Label heightLabel = new Label("Height");
        gridPane.add(heightLabel,4,0);
        heightTextField = new TextField();
        heightTextField.setMaxWidth(100);
        gridPane.add(heightTextField,4,1);




        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,buttonHBox,gridPane,pane);
        vBox.setSpacing(2.0);
        vBox.setPadding(new Insets(5));

        root.getChildren().add(vBox);
    }

    public void selectLocation(Location l){
        controller.setSelected(l);
        setTextFieldsWithSelected();
        removeButton.setDisable(false);
    }
    public void clearTextFields(){
        nameTextField.clear();
        xTextField.clear();
        yTextField.clear();
        widthTextField.clear();
        heightTextField.clear();
    }

    public void clearSelected(){
        controller.clearSelected();
        clearTextFields();
        removeButton.setDisable(true);
    }

    public void removeSelected(){
        String name = controller.getSelected().getLocationName();
        pane.getChildren().removeIf(node -> ((LocationSettingTextRectangle)node).getText().equals(name));
        controller.removeSelected();
        clearTextFields();
        removeButton.setDisable(true);
    }

    public void setTextFieldsWith(Location l){
        nameTextField.setText(l.getLocationName());
        xTextField.setText(l.getLocationX()+"");
        yTextField.setText(l.getLocationY()+"");
        widthTextField.setText(l.getLocationWidth()+"");
        heightTextField.setText(l.getLocationHeight()+"");
    }
    public void setTextFieldsWithSelected(){
        Location l = controller.getSelected();
        setTextFieldsWith(controller.getSelected());
    }

    public LocationSettingTextRectangle createRectangle(){
        return new LocationSettingTextRectangle(this);
    }

    public void populate(){
        for (Location l: controller.getAllLocation()){
            LocationSettingTextRectangle rect = new LocationSettingTextRectangle(l,this);
            pane.getChildren().add(rect);
            NodeDragResizer.makeDragResizable(rect);
        }
    }
}
