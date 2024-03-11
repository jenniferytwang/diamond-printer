package com.diamondprinter;

import org.springframework.stereotype.Component;

@Component
public class DiamondPrinter {
    public String printDiamond(char midChar) {
        if (!Character.isUpperCase(midChar) || midChar < 'A' || midChar > 'Z') {
            throw new IllegalArgumentException("Invalid character: " + midChar);
        }

        int size = midChar - 'A' + 1;
        StringBuilder diamond = new StringBuilder();

        // Print upper half of the diamond
        for (char currentChar = 'A'; currentChar <= midChar; currentChar++) {
            diamond.append(printRow(size, currentChar));
        }

        // Print lower half of the diamond excluding the middle row
        for (char currentChar = (char) (midChar - 1); currentChar >= 'A'; currentChar--) {
            diamond.append(printRow(size, currentChar));
        }

        return diamond.toString();
    }

    private String printRow(int size, char currentChar) {
        int spaces = size - (currentChar - 'A') - 1;
        StringBuilder row = new StringBuilder();

        // Append leading spaces
        row.append(" ".repeat(spaces));

        // Append current character
        row.append(currentChar);

        // Append middle spaces (except for 'A')
        if (currentChar != 'A') {
            row.append(" ".repeat(2 * (currentChar - 'A') - 1));
            row.append(currentChar);
        }

        // Append trailing spaces
        row.append(" ".repeat(spaces));

        // Append newline character
        row.append("\n");

        return row.toString();
    }
}
