package com.c57lee.possystemdemo.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    private long employeeID;
    private String employeeFname;
    private String employeeLname;
    private Date dob;
    private Date startDate = Date.valueOf(LocalDate.now());
    private Date endDate;
    private int socialSecurity;

    public Employee(){

    }

    public Employee(long employeeID, String employeeFname, String employeeLname, Date dob, Date startDate, Date endDate, int socialSecurity) {
        this.employeeID = employeeID;
        this.employeeFname = employeeFname;
        this.employeeLname = employeeLname;
        this.dob = dob;
        this.startDate = startDate;
        this.endDate = endDate;
        this.socialSecurity = socialSecurity;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeFname() {
        return employeeFname;
    }

    public void setEmployeeFname(String employeeFname) {
        this.employeeFname = employeeFname;
    }

    public String getEmployeeLname() {
        return employeeLname;
    }

    public void setEmployeeLname(String employeeLname) {
        this.employeeLname = employeeLname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employeeID +
                ", employee_fname=" + employeeFname +
                ", employee_lname=" + employeeLname +
                ", dob=" + dob +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", social_security=" + socialSecurity +
                '}';
    }
}
