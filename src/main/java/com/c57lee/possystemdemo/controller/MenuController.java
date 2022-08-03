package com.c57lee.possystemdemo.controller;

import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.persistence.DBOperations;

import java.util.List;

public class MenuController {

    DBOperations dbOperations;

    public MenuController() {
        this.dbOperations = new DBOperations();
    }

    public List<Menu> getMenuList(){
        return dbOperations.getAll("Menu");
    }
}
