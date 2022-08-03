package com.c57lee.possystemdemo.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private long itemID;
    private String itemName;
    private double price;
    private String category;
    private String note;
    @ManyToOne
    private Client client;
    private Timestamp timeCreated;
    @ManyToOne
    private Employee employee;


    public Item(){

    }

    public Item(String itemName, double price, String category, String note, Client client, Employee employee) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.note = note;
        this.client = client;
        this.timeCreated = Timestamp.valueOf(LocalDateTime.now());
        this.employee = employee;
    }

    public Item (Menu menuItem, Client client,Employee employee){
        this.itemName = menuItem.getMenuName();
        this.price = menuItem.getPrice();
        this.category = menuItem.getCategory();
        this.note = "";
        this.client = client;
        this.timeCreated = Timestamp.valueOf(LocalDateTime.now());
        this.employee = employee;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getNote() {
        return note;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", note='" + note + '\'' +
                ", client=" + client +
                ", timeCreated=" + timeCreated +
                ", employee=" + employee +
                '}';
    }

}
