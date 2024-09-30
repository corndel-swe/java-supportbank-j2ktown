 package com.corndel.supportbank.exercises;

 import picocli.CommandLine.Command;

 @Command(name = "color", description = "What color should you wear today?")
 public class ColorCommand implements Runnable {
 /**
 * Print out a randomly chosen color to wear today.
 */
 @Override
 public void run() {
 String[] colors = { "red", "green", "blue", "yellow" };
 String color = colors[(int) (Math.random() * colors.length)];

 String msg = String.format("Today you should wear %s!", color);
 System.out.println(msg);
 }
 }