package com.corndel.supportbank.services;

import com.corndel.supportbank.models.Currency;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "summarise", description = "")
public class TransactionSummariseService implements Runnable {

    @Parameters(index = "0")
    private int amount;

    @Parameters(index = "1")
    private String currencyFrom;

    @Parameters(index = "2")
    private String currencyTo;


    @Override
    public void run() {
        // pass the amount, currencyfrom, currency to the buisness method to find the return.
        Currency currency = new Currency();
        double response = currency.convertCurrency(amount,currencyFrom,currencyTo);
        //return response;

    }
}


