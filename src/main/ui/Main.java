package ui;

import java.io.FileNotFoundException;

// Main class
// CITATION: modeled from the JsonSerializatioinDemo
public class Main {
    public static void main(String[] args) {
        try {
            new StriveApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
