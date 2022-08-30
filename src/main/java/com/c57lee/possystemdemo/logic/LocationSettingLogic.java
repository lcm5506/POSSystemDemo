package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Location;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;
import java.util.Optional;

public class LocationSettingLogic {

    DAO myDAO;

    public LocationSettingLogic(){
        this.myDAO = DAO.getInstance();
    }

    public void addLocation(Location l){
        myDAO.save(l);
    }

    public void updateLocation(Location l){
        myDAO.update(l);
    }

    public void removeLocation(Location l){
        myDAO.remove(l);
    }

    public List<Location> getAllLocation(){
        return myDAO.findAll(Location.class);
    }

    public boolean contains(Location l){
        Optional<Location> searched = myDAO.findByID(Location.class,l.getLocationName());
        if (searched.isPresent())
            return true;
        return false;
    }
}
