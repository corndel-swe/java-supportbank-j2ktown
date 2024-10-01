package com.corndel.supportbank.services;

import com.corndel.supportbank.exercises.UserId;
import com.corndel.supportbank.models.Currency;
import com.corndel.supportbank.models.Transaction;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Command(name = "list", description = "")
public class TransactionListService implements Runnable {

    @Parameters(index = "0")
    private String fileName;

    @Parameters(index = "1")
    private String id;

    @Parameters(index = "2")
    private String idSurname;



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

    public List<Transaction> transactionListByUser(String userId, List<Transaction> transactions) throws IOException {
        List<Transaction> userIdTransactions = new ArrayList<>();
        for (Transaction transaction: transactions){
            if (((transaction.from).equals(userId)) || (transaction.to.equals(userId))){
                userIdTransactions.add(transaction);
            }
        }
        // Print the transactions for the user
        System.out.println(String.format("%s transactions:", userId));
        for (Transaction transaction : userIdTransactions) { // Use userIdTransactions here
            System.out.println(String.format("Date: %s, From: %s, To: %s, Narrative: %s, Amount: %.2f",
                    transaction.date, transaction.from, transaction.to, transaction.narrative, transaction.amount));
        }
        return userIdTransactions;
    }




    @Override
    public void run() {
        try {
            TransactionListService transactionListService = new TransactionListService();
            List<Transaction> transactions = transactionListService.getTransactions(fileName);
            String userId = id + " " + idSurname;
            transactionListService.transactionListByUser(userId,transactions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}