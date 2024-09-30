package com.corndel.supportbank;
import com.corndel.supportbank.Controllers.BillController;
import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "Supportbank", description = "banking",  subcommands = {BillController.class})
public class SupportBank {

  public static void main(String[] args) {
    CommandLine cli = new CommandLine(new SupportBank());
    int exitCode = cli.execute(args);
    System.exit(exitCode);

  }
}
