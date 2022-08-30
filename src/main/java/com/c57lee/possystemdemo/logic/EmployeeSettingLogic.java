package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Employee;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;

public class EmployeeSettingLogic {

    DAO myDAO;

    public EmployeeSettingLogic(){
        this.myDAO = DAO.getInstance();
    }

    public void addEmployee(Employee e){
        myDAO.save(e);
    }

    public void updateEmployee(Employee e){
        myDAO.update(e);
    }

    public void deleteEmployee(Employee e){
        myDAO.remove(e);
    }

    public List<Employee> getAllEmployee(){
        return myDAO.findAll(Employee.class);
    }

}
