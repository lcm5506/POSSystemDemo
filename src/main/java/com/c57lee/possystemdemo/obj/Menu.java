package com.c57lee.possystemdemo.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Menu {
    @Id
    private long menuID;
    private String menuName;
    private double price;
    private String category;

    public Menu() {
    }

    public Menu(long menuID, String menuName, double price, String category) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public void setMenuID(long menuID) {
        this.menuID = menuID;
    }

    public long getMenuID() {
        return menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
}
