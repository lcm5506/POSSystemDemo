package com.c57lee.possystemdemo.userinterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class SettingUI {

    Stage stage;
    Scene scene;
    BorderPane root;

    public SettingUI(Stage stage){
        this.stage = stage;
        root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);
        initialize();
        stage.show();
    }

    public void initialize(){

        drawButtons();

    }

    public void drawButtons(){
        Label titleLabel = new Label("Settings");
        titleLabel.setFont(new Font("Arial",30));
        titleLabel.setPadding(new Insets(5));
        root.setTop(titleLabel);

        GridPane buttonPane = new GridPane();
        buttonPane.setPadding(new Insets(5));
        buttonPane.setHgap(5);
        buttonPane.setVgap(5);

        Button employeeSettingButton = new Button("Employee Setting");
        buttonStyling(employeeSettingButton);
        employeeSettingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new EmployeeSettingUI(new Stage());
            }
        });
        buttonPane.add(employeeSettingButton,0,0);

        Button menuSettingButton = new Button("Menu Setting");
        buttonStyling(menuSettingButton);
        menuSettingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new MenuSettingUI(new Stage());
            }
        });
        buttonPane.add(menuSettingButton,1,0);

        Button locationSettingButton = new Button("Location Setting");
        buttonStyling(locationSettingButton);
        locationSettingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new LocationSettingUI(new Stage());
            }
        });
        buttonPane.add(locationSettingButton,2,0);





        root.setCenter(buttonPane);
    }

    public void buttonStyling(Button b){
        b.setFont(new Font("Arial",20));
        b.setTextAlignment(TextAlignment.CENTER);
    }
}
