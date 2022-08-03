package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;

public class MenuSettingLogic {

    DAO<Menu,Long> dao;

    public MenuSettingLogic() {
        this.dao = new DAO<>();
    }

    public void addMenu(Menu m){
        dao.save(m);
    }

    public void updateMenu(Menu m){
        dao.update(m);
    }

    public void removeMenu(Menu m){
        dao.remove(m);
    }

    public List<Menu> getAllMenu(){
        return dao.findAll(Menu.class);
    }
}
