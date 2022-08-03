package com.c57lee.possystemdemo.controller;

import com.c57lee.possystemdemo.logic.LocationSettingLogic;
import com.c57lee.possystemdemo.obj.Location;

import java.util.List;

public class LocationSettingController {

    LocationSettingLogic logic;
    Location selected;
    public LocationSettingController(){

        logic = new LocationSettingLogic();
        selected = null;
    }

    public void addLocation(Location l){
        logic.addLocation(l);
    }

    public void updateLocation(Location l){
        logic.updateLocation(l);
    }

    public void removeLocation(Location l){
        logic.removeLocation(l);
    }

    public List<Location> getAllLocation(){
        return logic.getAllLocation();
    }
    public boolean contains(Location l){
        return logic.contains(l);
    }
    public void setSelected(Location l){
        this.selected = l;
    }

    public Location getSelected(){
        return selected;
    }

    public boolean isSelected(){
        if (selected != null)
            return true;
        return false;
    }

    public void removeSelected(){
        if(isSelected())
            removeLocation(selected);
        clearSelected();
    }

    public void clearSelected(){
        selected = null;
    }
}
