package com.corndel.supportbank;
import com.corndel.supportbank.Controllers.BillController;
import com.corndel.supportbank.Controllers.CurrencyController;
import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "supportbank", description = "banking",  subcommands = {BillController.class, CurrencyController.class} )
public class SupportBank {

  public static void main(String[] args) {
    CommandLine cli = new CommandLine(new SupportBank());
    int exitCode = cli.execute(args);
    System.exit(exitCode);

  }
}
