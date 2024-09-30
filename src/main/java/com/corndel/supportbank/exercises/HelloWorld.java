package com.corndel.supportbank.exercises;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "hello", description = "A CLI hello tracker")
public class HelloWorld implements Runnable{

    @Parameters(index = "0", description = "The name to greet", defaultValue = "World")
    private String name;

    @Override
    public void run(){
        String msg = String.format("Hello, %s!", name);
        System.out.println(msg);
    }

    public static void main(String[] args) {
        CommandLine cli = new CommandLine(new HelloWorld());
        int exitCode = cli.execute(args);
        System.exit(exitCode);
    }

}
