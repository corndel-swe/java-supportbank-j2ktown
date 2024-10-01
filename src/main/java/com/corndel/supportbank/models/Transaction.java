package com.corndel.supportbank.models;

public class Transaction {
    public String date;
    public String from;
    public String to;
    public String narrative;
    public double amount;

    public Transaction(String date, String from, String to, String narrative, double amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = amount;
    }
    // String representation of the Transaction object
    public String toString() {
        return String.format("Transaction{date='%s', from='%s', to='%s', narrative='%s', amount=%.2f}",
                date, from, to, narrative, amount);
    }

    public static String[] fromCSV(String row) {
        String[] parts = row.split(",");
        return parts;
    }

}





