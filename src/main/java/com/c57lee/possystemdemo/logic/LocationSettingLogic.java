package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Location;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;
import java.util.Optional;

public class LocationSettingLogic {

    DAO<Location,String> dao;

    public LocationSettingLogic(){
        dao = new DAO<>();
    }

    public void addLocation(Location l){
        dao.save(l);
    }

    public void updateLocation(Location l){
        dao.update(l);
    }

    public void removeLocation(Location l){
        dao.remove(l);
    }

    public List<Location> getAllLocation(){
        return dao.findAll(Location.class);
    }

    public boolean contains(Location l){
        Optional<Location> searched = dao.findByID(Location.class,l.getLocationName());
        if (searched.isPresent())
            return true;
        return false;
    }
}
