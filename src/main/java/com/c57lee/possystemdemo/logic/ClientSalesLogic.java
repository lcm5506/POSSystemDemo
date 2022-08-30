package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.*;
import com.c57lee.possystemdemo.persistence.DAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientSalesLogic {


    DAO myDAO;
    // client field is in persistent state.
    Client client=null;
    Employee employee;
    Location location;

    public ClientSalesLogic(){
        this.myDAO = DAO.getInstance();
    }
    public ClientSalesLogic(Client client){
        this.myDAO = DAO.getInstance();
        this.client = client;
        this.employee = client.getEmployee();
        this.location  = client.getLocation();
    }

    public boolean setClient(long clientID){
        Optional<Client> found = myDAO.findByID(Client.class,clientID);
        if (found.isPresent()){
            this.client = found.get();
            return true;
        }
        return false;

    }

    public void setClient(Client client){
        myDAO.save(client);
        this.client = client;
    }

    public void addItems(List<Item> menuItems){
        menuItems.stream().forEach(i->myDAO.save(i));
        client.getItems().addAll(menuItems);
//        for (Menu m: menuItems){
//            Item tempItem = new Item(m, client);
//            client.getItems().add(tempItem);
//        }
        myDAO.update(client);
    }

    public void changeLocation(Location newLocation){
        client.setLocation(newLocation);
        myDAO.update(client);
    }

    public void changeEmployee(Employee newEmployee){
        client.setEmployee(newEmployee);
        myDAO.update(client);
    }

    public void setEndTimeToNow(){
        client.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        myDAO.update(client);
    }

    public void addPayment(Payment payment){
        myDAO.save(payment);
        client.getPayments().add(payment);
        myDAO.update(client);
    }

    public Location getLocationByName(String locationName){
        return (Location)myDAO.findByID(Location.class,locationName).orElse(null);
    }

    public Employee getEmployeeByID(long employeeID){
        return (Employee)myDAO.findByID(Employee.class,employeeID).orElse(null);
    }

    public void addClientToLocation(Location location, Client client){
        location.getClients().add(client);
        myDAO.update(location);
    }

    public Client getCurrentClient(){
        return client;
    }

    public Location getCurrentLocation(){
        return location;
    }

    public Employee getEmployeeByID(){
        return client.getEmployee();
    }

    public Location getLocationByName(){
        return client.getLocation();
    }

    public void removeItemFromClient(Item item){
        if (client.getItems().contains(item))
            client.getItems().remove(item);
        else System.out.println("Such item does not exist.");
        myDAO.update(client);
    }

    public boolean isClientSet(){
        if (client == null)
            return false;
        else return true;
    }

    public void setLocationByName(String locationName){
        this.location = getLocationByName(locationName);
    }

    public void createNewClient(Employee employee, Location location, int head_count){
        Client newClient = new Client(employee, location, Timestamp.valueOf(LocalDateTime.now()), head_count, new ArrayList<Item>(), new ArrayList<Payment>());
        location.getClients().add(newClient);
        myDAO.update(location);
        myDAO.save(newClient);
        this.client = newClient;
    }
}
