package com.diamondprinter;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.util.Scanner;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DiamondPrinterApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DiamondPrinterApplication.class, args);
        DiamondPrinter diamondPrinter = context.getBean(DiamondPrinter.class);
        processInput(System.in, diamondPrinter);
    }

    static void processInput(InputStream inputStream, DiamondPrinter diamondPrinter) {
        Scanner scanner = new Scanner(inputStream);
        while (true) {
            System.out.println("Enter a character (A, B, C, etc.), or type 'exit' to quit: \n");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("EXIT")) {
                System.out.println("Program end.");
                break;
            }

            if (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) > 'Z') {
                System.out.println("Invalid input. Please enter a single character (A, B, C, etc.).");
            } else {
                char character = input.charAt(0);
                String diamond = diamondPrinter.printDiamond(character);
                System.out.println("Diamond for '" + character + "':");
                System.out.println(diamond);
            }
        }
    }
}
