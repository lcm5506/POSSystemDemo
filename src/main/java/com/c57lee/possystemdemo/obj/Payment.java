package com.c57lee.possystemdemo.obj;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private long paymentID;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    private String paymentMethod;
    private double requestedAmount;
    private double paidAmount;



    public Payment() {
    }

    public Payment(Client client, Employee employee, String paymentMethod, double RequestedAmount, double paidAmount) {
        this.client = client;
        this.employee = employee;
        this.paymentMethod = paymentMethod;
        this.requestedAmount = RequestedAmount;
        this.paidAmount = paidAmount;
    }

    public long getPaymentID() {
        return paymentID;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getTipAmount(){
        return paidAmount - requestedAmount;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", client=" + client +
                ", employee=" + employee +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentTargetAmount=" + requestedAmount +
                ", paymentTotalAmount=" + paidAmount +
                '}';
    }
}
