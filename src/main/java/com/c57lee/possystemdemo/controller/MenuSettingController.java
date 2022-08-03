package com.c57lee.possystemdemo.controller;

import com.c57lee.possystemdemo.logic.MenuSettingLogic;
import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.userinterface.MenuSettingUI;

import java.util.List;

public class MenuSettingController {

    MenuSettingLogic logic;
    MenuSettingUI ui;


    public MenuSettingController(MenuSettingUI ui){
        logic = new MenuSettingLogic();
        this.ui = ui;
    }

    public void addMenu(Menu m){
        logic.addMenu(m);
    }

    public void updateMenu(Menu m){
        logic.updateMenu(m);
    }

    public void removeMenu(Menu m){
        logic.removeMenu(m);
    }

    public List<Menu> getAllMenu(){
        return logic.getAllMenu();
    }
}

