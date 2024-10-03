package com.corndel.supportbank.services;

import com.corndel.supportbank.Api.ExchangeRateApi;
import com.corndel.supportbank.Controllers.CurrencyController;
import com.corndel.supportbank.models.Currency;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "convert", description = "exchangec different currencys")
public class CurrencyService implements Runnable {

    @Parameters(index = "0")
    private int amount;

    @Parameters(index = "1")
    private String currencyFrom;

    @Parameters(index = "2")
    private String currencyTo;

    public double convertCurrency(int amount, String currencyFrom, String currencyTo) throws Exception {
        // validate input
        double response;

        if (amount < 0) {
            System.out.println("amount provide is not valid");
        }

        //Get exchange rates
        double exchangeRate = ExchangeRateApi.getExchangeRate(currencyFrom, currencyTo);
        double currencyToAmount = exchangeRate * amount;

        System.out.println(String.format("By converting the amount %d %s to %s you get %.2f %s ", amount, currencyFrom, currencyTo, currencyToAmount, currencyTo));

        return currencyToAmount;
    }

    @Override
    public void run() {
        try {
            CurrencyService currencyService = new CurrencyService();
            currencyService.convertCurrency(amount,currencyFrom,currencyTo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
