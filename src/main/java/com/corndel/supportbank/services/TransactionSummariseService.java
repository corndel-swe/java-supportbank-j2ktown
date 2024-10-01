package com.corndel.supportbank.services;

import com.corndel.supportbank.models.Currency;
import com.corndel.supportbank.models.Transaction;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command(name = "summarise", description = "")
public class TransactionSummariseService implements Runnable {

    @Parameters(index = "0", description = "The name of the file to summarize transactions from.")
    public String fileName;

    public List<Transaction> getTransactions(String fileName) throws IOException {
        System.out.println("In getTransactions method. File name: " + fileName);
        //FileIO file = new FileIO(fileName);  // Create the file with the correct path
        Path filePath = Paths.get("src", "data", "transactions", fileName);
        List<String> lines = Files.readAllLines(filePath);

        //List<String> lines = file.readFile();  // Read file contents
        System.out.println("File read successfully. Number of lines: " + lines.size());

        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) { // Start from 1 to skip header
            String[] transactionString = Transaction.fromCSV(lines.get(i));
            String date = transactionString[0];
            String from = transactionString[1];
            String to = transactionString[2];
            String narrative = transactionString[3];
            double amount = Double.parseDouble(transactionString[4]);
            transactions.add(new Transaction(date, from, to, narrative, amount));
        }
        System.out.println("Transactions parsed successfully.");
        return transactions;
    }

    public static Map<String, Double> listBalances(List<Transaction> transactions) {
        System.out.println("Calculating account balances...");
        Map<String, Double> accounts = new HashMap<>();
        for (Transaction transaction : transactions) {
            String userFrom = transaction.from;
            String userTo = transaction.to;
            double amount = transaction.amount;

            // Update "from" account balance
            accounts.put(userFrom, accounts.getOrDefault(userFrom, 0.0) - amount);

            // Update "to" account balance
            accounts.put(userTo, accounts.getOrDefault(userTo, 0.0) + amount);
        }

        // Print the balances
        System.out.println("Account balances:");
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
        return accounts;
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting summarise command...");
            List<Transaction> transactions = getTransactions(this.fileName);
            System.out.println("Transactions loaded successfully.");
            // Print balance list after transactions
            listBalances(transactions);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




