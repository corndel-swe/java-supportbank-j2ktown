package com.corndel.supportbank.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import io.github.cdimascio.dotenv.Dotenv;



public class ExchangeRateApi {

    public static double getExchangeRate(String currencyFrom, String CurrencyTo) throws Exception {

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPEN_EXCHANGE_RATES_APP_ID");

        //we can update currency from so if we paid for open exchange we can update the base
        String url = String.format("https://openexchangerates.org/api/latest.json?app_id=%s&base=USD", apiKey);

        var response = Unirest
                .get(url)
                .header("Accept", "application/json")
                .asString();

        String json = response.getBody();
        System.out.println(json);

        ObjectMapper objectMapper = new ObjectMapper();
        var tree = objectMapper.readTree(json);
        var rates = tree.get("rates");

        var usdToCurrencyFromRate = rates.get(currencyFrom).asDouble(); // convert json node to double
        var usdToCurrencyToRate = rates.get(CurrencyTo).asDouble();

        double exchangeRate = usdToCurrencyToRate / usdToCurrencyFromRate;

        return exchangeRate;
    }



}


