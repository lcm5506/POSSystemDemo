open module com.c57lee.possystemdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    exports com.c57lee.possystemdemo;
    exports com.c57lee.possystemdemo.obj;
    exports com.c57lee.possystemdemo.userinterface;
    exports com.c57lee.possystemdemo.controller;
    exports com.c57lee.possystemdemo.userinterface.component;
    exports com.c57lee.possystemdemo.logic;
}