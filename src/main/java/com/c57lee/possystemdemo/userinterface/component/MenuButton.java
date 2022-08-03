package com.c57lee.possystemdemo.userinterface.component;

import com.c57lee.possystemdemo.obj.Menu;
import javafx.scene.control.Button;

public class MenuButton extends Button {
    Menu menu;

    public MenuButton(Menu menu){

        this.menu = menu;
        setTextBasedOnItem();
        this.setPrefSize(100,100);
    }

    public void setTextBasedOnItem(){
        String text = menu.getMenuName()+"\n $"+menu.getPrice();
        this.setText(text);
    }

    public Menu getMenu() {
        return menu;
    }
}
