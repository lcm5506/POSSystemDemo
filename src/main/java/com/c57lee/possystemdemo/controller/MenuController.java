package com.c57lee.possystemdemo.controller;

import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;

public class MenuController {

    DAO dao;

    public MenuController() {
        this.dao = DAO.getInstance();
    }

    public List<Menu> getMenuList(){
        return dao.findAll(Menu.class);
    }
}
