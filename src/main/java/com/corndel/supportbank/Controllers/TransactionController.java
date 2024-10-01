package com.corndel.supportbank.Controllers;
import com.corndel.supportbank.services.TransactionListService;
import com.corndel.supportbank.services.TransactionSummariseService;
import picocli.CommandLine.Command;

@Command(name = "transaction", description = "Manages transactions",  subcommands = {TransactionSummariseService.class, TransactionListService.class})
public class TransactionController {


}