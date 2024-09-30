package com.corndel.supportbank.models;

public class Currency {
    final double gbpToUSD = 1.33;
    final double usdToGBP = 0.75;


    public double convertCurrency(int amount, String currencyFrom, String currencyTo) {
        // validate input
        double response;

        if (amount < 0){
            System.out.println("amount provide is not valid");
        }
        if ( (!currencyTo.equals("gbp") || (!currencyTo.equals("gbp"))) && (!currencyTo.equals("gbp") || (!currencyTo.equals("gbp")))) {
            System.out.println("CurrencyFrom/To is not gbp/ usd");
        }

        if (currencyTo.equals("gbp"))
            { response = amount * gbpToUSD;}
        else
            { response = amount * usdToGBP;}

        System.out.println(String.format("By converting the amount %d %s to %s you get %.2f %s ", amount,currencyFrom,currencyTo, response, currencyTo));

        return response;

    }
}
