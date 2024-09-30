package com.corndel.supportbank.services;
import com.corndel.supportbank.models.Bill;
import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "split", description = "Split the bill")
public class BillService implements Runnable{

    @CommandLine.Parameters(index = "0")
    private int total;

    @CommandLine.Parameters(index = "1")
    private int numberOfPeople;

    @Override
    public void run() {
        Bill createBill = new Bill(total);
        double response = createBill.split(numberOfPeople);
        System.out.println("The the bill is " + response + " each.");
    }
}