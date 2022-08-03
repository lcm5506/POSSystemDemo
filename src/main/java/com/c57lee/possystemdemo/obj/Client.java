package com.c57lee.possystemdemo.obj;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private long clientID;
    @ManyToOne
    private Employee employee;
    // it maybe logical to make client the owning side of relationship with location although
    // it is not recommended in practice.
    @ManyToOne
    private Location location;
    private Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp endTime;
    private int headCount;
    @OneToMany
    private List<Item> items;
    @OneToMany(mappedBy = "client")
    private List<Payment> payments;

    public Client(){

    }

    public Client(Employee employee, Location location, Timestamp startTime, int headCount, List<Item> items, List<Payment> payments) {
        this.employee = employee;
        this.location = location;
        this.startTime = startTime;
        this.headCount = headCount;
        this.items = items;
        this.payments = payments;
    }

    public long getClientID() {
        return clientID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Location getLocation() {
        return location;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public int getHeadCount() {
        return headCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public double getTotal(){
        double totalFromSalesList = 0;
        for (Item i: items){
            totalFromSalesList += i.getPrice();
        }
        return totalFromSalesList;
    }

    public double getTotalAfterTax(){
        double taxRate = 0.07;
        return getTotal() * (1+taxRate);

    }

    public double getTotalFromPaymentList(){
        double totalPaidAmountFromPaymentList = 0;
        for (Payment p: payments){
            totalPaidAmountFromPaymentList += p.getPaidAmount();
        }
        return totalPaidAmountFromPaymentList;
    }

    public double getTotalTipFromPaymentList(){
        double totalTipFromPaymentList = 0;
        for (Payment p: payments){
            totalTipFromPaymentList += p.getTipAmount();
        }
        return totalTipFromPaymentList;
    }




}
