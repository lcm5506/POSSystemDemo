package com.c57lee.possystemdemo.userinterface.component;

import com.c57lee.possystemdemo.obj.Client;
import com.c57lee.possystemdemo.obj.Location;
import javafx.scene.control.Button;

import java.util.List;

public class LocationButton extends Button {

    Location location;

    public LocationButton(Location location) {
        super();
        this.location = location;
        setTextBasedOnClient();
        setSizeAndLayout();
    }

    public Location getLocation() {
        return location;
    }

    public void setTextBasedOnClient(){
        List<Client> clientList = location.getClients();
        if (clientList.isEmpty())
            this.setText(location.getLocationName());
        for (int i = clientList.size()-1; i>=0; i--){
            if (clientList.get(i).getEndTime() == null) {
                this.setText(location.getLocationName() + "\n" + clientList.get(i).getEmployee().getEmployeeLname());
                break;
            }
        }
        this.setText(location.getLocationName());
    }

    public void setSizeAndLayout(){
        this.setPrefSize(location.getLocationWidth(), location.getLocationHeight());
        this.setLayoutX(location.getLocationX());
        this.setLayoutY(location.getLocationY());
    }


}
