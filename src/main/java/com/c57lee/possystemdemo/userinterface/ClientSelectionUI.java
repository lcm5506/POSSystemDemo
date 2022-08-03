package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.logic.ClientSelectionLogic;
import com.c57lee.possystemdemo.obj.Location;
import com.c57lee.possystemdemo.userinterface.component.LocationButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ClientSelectionUI implements EventHandler<ActionEvent> {

    Stage stage;
    Scene scene;
    BorderPane root;

    final double WINDOW_WIDTH = 800;
    final double WINDOW_HEIGHT = 800;

    HashMap<String,Location> locationMap;
    ClientSelectionLogic clientSelectionLogic;

    public ClientSelectionUI(Stage stage){
        this.stage = stage;
        locationMap = new HashMap<>();
        clientSelectionLogic = new ClientSelectionLogic();
        root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);

        initialize(root);
        stage.show();
    }
    
    public void initialize(BorderPane root){
        
        DrawInfoPane(root);
        DrawFunctionButtonPane(root);
        DrawLocationPane(root);
        
    }

    private void DrawLocationPane(BorderPane root) {
        findAndSetLocationMap();
        Pane pane = new Pane();
        double locationPaneHeight = 800;
        double locationPaneWidth = 800;
        pane.setPrefSize(locationPaneWidth,locationPaneHeight);
        pane.setStyle("-fx-background-color: black;");

        for (String locName: locationMap.keySet()){
            Location loc = locationMap.get(locName);
            LocationButton button = new LocationButton(loc);
            button.setOnAction(this);
            pane.getChildren().add(button);
        }
        root.setCenter(pane);
    }

    private void DrawFunctionButtonPane(BorderPane root) {
    }

    private void DrawInfoPane(BorderPane root) {
    }

    private void findAndSetLocationMap(){
        this.locationMap = clientSelectionLogic.getLocationMap();
    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource().getClass() == LocationButton.class){
            ClientSalesUI clientUI = new ClientSalesUI(new Stage(),((LocationButton)e.getSource()).getLocation().getLocationName());
        }
    }
}
