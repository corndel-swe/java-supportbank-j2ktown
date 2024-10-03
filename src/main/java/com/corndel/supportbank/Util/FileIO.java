//package com.corndel.supportbank.Util;
//
//import com.corndel.supportbank.models.Transaction;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class FileIO {
//
//    public Path filePath;
//
//    public FileIO(String fileName) throws IOException {
//        // Print the constructed file path to make sure it's correct
//        System.out.println("Constructed file path: " + Paths.get("src", "data", "transactions", fileName).toAbsolutePath());
//        this.filePath = Paths.get("src", "data", "transactions", fileName);
//    }
//
//    public List<String> readFile() throws IOException {
//        // Ensure the file path being used is printed
//        System.out.println("Reading file from path: " + this.filePath.toAbsolutePath());
//        List<String> lines = Files.readAllLines(this.filePath); // Reading the file
//        System.out.println("File read successfully. Lines count: " + lines.size());
//        return lines;
//    }
//}

