package ui;

import exception.EmptyGoalListException;

import java.io.FileNotFoundException;

// Main class
// CITATION: modeled from the JsonSerializatioinDemo
public class Main {
    public static void main(String[] args) throws EmptyGoalListException {
        try {
            new StriveApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
