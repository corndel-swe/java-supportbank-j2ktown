package com.corndel.supportbank.Controllers;
import com.corndel.supportbank.services.BillService;
import picocli.CommandLine.Command;

@Command(name = "bill", description = "banking",  subcommands = {BillService.class})
public class BillController {

}

