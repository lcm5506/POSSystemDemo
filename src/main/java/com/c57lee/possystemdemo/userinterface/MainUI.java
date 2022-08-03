package com.c57lee.possystemdemo.userinterface;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class MainUI {

    Stage stage;
    Scene scene;
    BorderPane root;

    public MainUI(Stage stage){
        this.stage = stage;
        root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);
        initialize();
        stage.show();
    }

    public void initialize(){
        stage.setTitle("POS System Demo");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                showExitAlert(event);
            }
        });
        drawMainButtons();
        drawInfoPane();
        drawLogo();
    }

    public void drawMainButtons(){
        HBox buttonHBox = new HBox();

        Button tableButton = new Button("Table");
        tableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ClientSelectionUI(createNewSecondaryStage());


            }
        });
        buttonStyling(tableButton);

        Button startDayButton = new Button("Start New Day");
        startDayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        buttonStyling(startDayButton);

        Button endDayButton = new Button("End Day");
        endDayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        buttonStyling(endDayButton);


        Button statisticsButton = new Button("Statistics");
        statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        buttonStyling(statisticsButton);

        Button settingButton = new Button("Setting");
        settingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SettingUI(createNewSecondaryStage());
            }
        });
        buttonStyling(settingButton);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               showExitAlert(event);
            }
        });
        buttonStyling(exitButton);

        buttonHBox.getChildren().addAll(tableButton,startDayButton,endDayButton,statisticsButton,settingButton,exitButton);
        root.setCenter(buttonHBox);
    }
    public void buttonStyling(Button b){
        b.setFont(new Font("Trebuchet MS",20));
        b.setPrefHeight(50);
        b.setBackground(Background.fill(Color.LIGHTGREY));
        b.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setBackground(Background.fill(Color.WHITE));
            }
        });
        b.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setBackground(Background.fill(Color.LIGHTGREY));
            }
        });
    }

    public Stage createNewSecondaryStage(){
        Stage secondaryStage = new Stage();
        secondaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.hide();
            }
        });
        secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.show();
            }
        });
        return secondaryStage;
    }

    public void showExitAlert(Event event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Do you want to exit the program?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
            System.exit(0);
        } else {
            event.consume();
        }
    }

    public void drawInfoPane(){


    }

    public void drawLogo(){

    }
}
