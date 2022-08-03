package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Location;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.HashMap;
import java.util.List;

public class ClientSelectionLogic {

    DAO myDAO;

    public ClientSelectionLogic() {

        this.myDAO = new DAO();
    }

    public HashMap<String, Location> getLocationMap(){
        HashMap<String,Location> locationHashMap = new HashMap<>();
        List<Location> locationList = myDAO.findAll(Location.class);
        for (Location l: locationList){
            locationHashMap.put(l.getLocationName(), l);
        }
        return locationHashMap;
    }

}
