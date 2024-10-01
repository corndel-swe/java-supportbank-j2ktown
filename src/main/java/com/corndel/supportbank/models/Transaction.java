package com.corndel.supportbank.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// supportbank transaction summarise <filename> – should output the names of each person, and their current balance according to the given file, e.g. Transactions2014.csv.
//
//supportbank transaction list <filename> <account name> – should print a list of every transaction, with the date and narrative, for that person’s account. For example, supportbank transaction list "jon a" would list all of Jon A’s transactions.


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String date;
    private String from;
    private String to;
    private String narrative;
    private double amount;
    private List<Transaction> transactions = new ArrayList<>();
    private Map<String, Double> accounts = new HashMap<>();;

    // Constructor
    public Transaction(String date, String from, String to, String narrative, double amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = amount;
    }

    // Method to get transactions from a CSV file
    public static List<Transaction> getTransactions(String fileName) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        // Read file
        Path filePath = Paths.get("src", "data", "transactions", fileName);

        // Check if the file exists
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + filePath);
        }

        // Convert to list
        List<String> lines = Files.readAllLines(filePath);

        // Check if there are at least 2 lines (headers + data)
        if (lines.size() < 2) {
            throw new IOException("Not enough data in the file: " + filePath);
        }

        // Process each line to split up and create transaction, starting from the second line
        for (int i = 1; i < lines.size(); i++) { // Start from 1 to skip header
            Transaction transaction = fromCSV(lines.get(i));
            transactions.add(transaction);
        }
        return transactions;
    }

    // Method to create a Transaction object from a CSV row
    public static Transaction fromCSV(String row) {
        // Split CSV into parts
        String[] parts = row.split(",");

        String date = parts[0];
        String from = parts[1];
        String to = parts[2];
        String narrative = parts[3];
        double amount = Double.parseDouble(parts[4]);


        // Create transaction object and return it
        return new Transaction(date, from, to, narrative, amount);
    }

    // String representation of the Transaction object
    @Override
    public String toString() {
        return String.format("Transaction{date='%s', from='%s', to='%s', narrative='%s', amount=%.2f}",
                date, from, to, narrative, amount);
    }

    public static Map<String, Double> listBalances(List<Transaction> transactions) {
        Map<String, Double> accounts = new HashMap<>();
        for (Transaction transaction : transactions) {
            String userFrom = transaction.from;
            String userTo = transaction.to;
            double amount = transaction.amount;

            // update from account
            double oldBalance = accounts.containsKey(userFrom) ? accounts.get(userFrom) : 0.0;
            double newBalance = oldBalance - amount;
            accounts.put(userFrom, newBalance);

            // update to account
            double oldBalance1 = accounts.containsKey(userTo) ? accounts.get(userTo) : 0.0;
            double newBalance1 = oldBalance1 + amount;
            accounts.put(userTo, newBalance1);
        }

        // print the balances
        System.out.println("Account balances:");
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Balance: " + entry.getValue());
            // print transaction
        }
        return accounts;
    }

    // Main method to run the application
    public static void main (String[] args) {
        try {
            List<Transaction> transactions = Transaction.getTransactions("Transactions2014.csv"); // Ensure the file extension is correct
            listBalances(transactions);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}




//
//    public static createTransaction fromCSV(String row) {
//        ArrayList<Transaction> list = new ArrayList<>();
//        String[] parts = row.split(",");
//        for (part:parts)
//        return new Transaction(parts[0], Integer.parseInt(parts[1]));
//    }

