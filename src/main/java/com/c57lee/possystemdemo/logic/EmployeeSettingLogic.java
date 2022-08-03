package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Employee;
import com.c57lee.possystemdemo.persistence.DAO;

import java.util.List;

public class EmployeeSettingLogic {

    DAO<Employee,Long> dao;

    public EmployeeSettingLogic(){
        dao  = new DAO<>();
    }

    public void addEmployee(Employee e){
        dao.save(e);
    }

    public void updateEmployee(Employee e){
        dao.update(e);
    }

    public void deleteEmployee(Employee e){
        dao.remove(e);
    }

    public List<Employee> getAllEmployee(){
        return dao.findAll(Employee.class);
    }

}
