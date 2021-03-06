package ui;

import java.io.FileNotFoundException;

// Main
public class Main {
    public static void main(String[] args) {
        try {
            new StriveApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
