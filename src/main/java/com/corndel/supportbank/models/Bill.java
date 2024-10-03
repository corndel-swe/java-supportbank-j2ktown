package com.corndel.supportbank.models;

public class Bill {

    int total;

    public Bill(int total){
        this.total = total;
    }

    public double split(double people) {
        return total / people;
    }
}