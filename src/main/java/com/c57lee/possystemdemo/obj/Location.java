package com.c57lee.possystemdemo.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Location {

    @Id
    private String locationName;
    private double locationX;
    private double locationY;
    private double locationWidth;
    private double locationHeight;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private List<Client> clients;

    public Location(){

    }

    public Location(String locationName, double locationX, double locationY, double locationWidth, double locationHeight) {
        this.locationName = locationName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationWidth = locationWidth;
        this.locationHeight = locationHeight;
        this.clients = new ArrayList<Client>();
    }

    public Location(String locationName, double locationX, double locationY, double locationWidth, double locationHeight, List<Client> clients) {
        this.locationName = locationName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationWidth = locationWidth;
        this.locationHeight = locationHeight;
        this.clients = clients;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public double getLocationWidth() {
        return locationWidth;
    }

    public double getLocationHeight() {
        return locationHeight;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setLocationWidth(double locationWidth) {
        this.locationWidth = locationWidth;
    }

    public void setLocationHeight(double locationHeight) {
        this.locationHeight = locationHeight;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getOpenClient(){
        for (int i = clients.size()-1; i >=0; i--){
            Client client = clients.get(i);
            if (client.getEndTime() == null)
                return client;
        }
        return null;
    }
}
