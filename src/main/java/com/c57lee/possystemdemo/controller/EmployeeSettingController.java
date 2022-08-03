package com.c57lee.possystemdemo.controller;

import com.c57lee.possystemdemo.logic.EmployeeSettingLogic;
import com.c57lee.possystemdemo.obj.Employee;
import com.c57lee.possystemdemo.userinterface.EmployeeSettingUI;

import java.util.List;

public class EmployeeSettingController {

    EmployeeSettingUI ui;
    EmployeeSettingLogic logic;

    public EmployeeSettingController(EmployeeSettingUI ui){
        this.ui = ui;
        this.logic = new EmployeeSettingLogic();
    }

    public void addEmployee(Employee e){
        logic.addEmployee(e);
    }

    public void updateEmployee(Employee e){
        logic.updateEmployee(e);
    }

    public void deleteEmployee(Employee e){
        logic.deleteEmployee(e);
    }

    public List<Employee> getAllEmployee(){
        return logic.getAllEmployee();
    }

}
