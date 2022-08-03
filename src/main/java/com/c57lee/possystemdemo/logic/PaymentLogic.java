package com.c57lee.possystemdemo.logic;

import com.c57lee.possystemdemo.obj.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentLogic {

    private double requestedAmount;

    public PaymentLogic(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public Payment getPaymentFromOutside(){
        return new Payment();
    }

    public Payment getPayment(){
        return new Payment();
    }

    public List<Payment> getAllPayments(){

        return new ArrayList<Payment>();
    }

}
