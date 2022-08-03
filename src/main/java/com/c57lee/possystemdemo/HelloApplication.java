package com.c57lee.possystemdemo;

import com.c57lee.possystemdemo.userinterface.MainUI;
import com.c57lee.possystemdemo.userinterface.SettingUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //sample data
//        Menu item1 = new Menu(1L,"item1",1.99,"category A");
//        Menu item2 = new Menu(2L,"item2",2.99,"category A");
//        Menu item3 = new Menu(3L,"item3",3.99,"category A");
//        Menu item4 = new Menu(4L,"item4",4.99,"category B");
//        Menu item5 = new Menu(5L,"item5",5.99,"category B");
//        Menu item6 = new Menu(6L,"item6",6.99,"category B");
//        Menu item7 = new Menu(7L,"item7",7.99,"category C");
//        Menu item8 = new Menu(8L,"item8",8.99,"category C");
//        Menu item9 = new Menu(9L,"item9",9.99,"category C");
//        Menu item10 = new Menu(10L,"item10",10.99,"category D");
//        Menu item11 = new Menu(11L,"item11",11.99,"category D");
//        Menu item12 = new Menu(12L,"item12",12.99,"category D");
//        Menu item13 = new Menu(13L,"item13",13.99,"category D");
//        Menu item14 = new Menu(14L,"item14",14.99,"category D");
//        Menu item15 = new Menu(15L,"item15",15.99,"category D");
//        Menu item16 = new Menu(16L,"item16",16.99,"RandomCategory");
//
//
//
//
//        DBOperations db = new DBOperations();
//        db.persist(item1);
//        db.persist(item2);
//        db.persist(item3);
//        db.persist(item4);
//        db.persist(item5);
//        db.persist(item6);
//        db.persist(item7);
//        db.persist(item8);
//        db.persist(item9);
//        db.persist(item10);
//        db.persist(item11);
//        db.persist(item12);
//        db.persist(item13);
//        db.persist(item14);
//        db.persist(item15);
//        db.persist(item16);
//
//
//
//        Location l1 = new Location("table 1", 5 , 5, 80, 80);
//        Location l2 = new Location("table 2", 100, 5, 80, 80);
//        Location l3 = new Location("table 3", 200, 5, 80, 80);
//        Location l4 = new Location("table 4", 5, 100, 80, 80);
//        Location l5 = new Location("table 5", 100, 100, 80, 80);
//        Location l6 = new Location("table 6", 200, 100, 80, 80);
//        Location l7 = new Location("table 7", 5, 200, 80, 80);
//        Location l8 = new Location("table 8", 100, 200, 80, 80);
//        Location l9 = new Location("table 9", 200, 200, 80, 80);
//        Location l10 = new Location("table 10", 30, 300, 180, 180);
//
//
//        db.persist(l1);
//        db.persist(l2);
//        db.persist(l3);
//        db.persist(l4);
//        db.persist(l5);
//        db.persist(l6);
//        db.persist(l7);
//        db.persist(l8);
//        db.persist(l9);
//        db.persist(l10);
//
//        Employee employee1 = new Employee(1L,"Charles","Lee", Date.valueOf("1988-01-25"),Date.valueOf(LocalDate.now()),null,1031129);
//        Employee employee2 = new Employee(2L,"Ken","Chump", Date.valueOf("1989-04-12"),Date.valueOf(LocalDate.now()),null,1011810);
//        db.persist(employee1);
//        db.persist(employee2);

        //new ClientSelectionUI(stage);
        new MainUI(stage);


    }


    public static void main(String[] args) {
        launch();
    }
}