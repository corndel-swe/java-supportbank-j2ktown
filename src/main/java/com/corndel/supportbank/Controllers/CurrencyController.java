package com.corndel.supportbank.Controllers;
import com.corndel.supportbank.services.BillService;
import com.corndel.supportbank.services.CurrencyService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "currency", description = "banking",  subcommands = {CurrencyService.class})
public class CurrencyController {


}
