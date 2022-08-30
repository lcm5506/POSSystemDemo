package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;

public class MenuSettingLogic {

    DAO myDAO;

    public MenuSettingLogic() {
        this.myDAO = DAO.getInstance();
    }

    public void addMenu(Menu m){
        myDAO.save(m);
    }

    public void updateMenu(Menu m){
        myDAO.update(m);
    }

    public void removeMenu(Menu m){
        myDAO.remove(m);
    }

    public List<Menu> getAllMenu(){
        return myDAO.findAll(Menu.class);
    }
}
