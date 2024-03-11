package com.diamondprinter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DiamondPrinterApplicationTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testProcessInputSuccess() {
        String input = "B\nexit\n"; // Valid input character followed by 'exit'
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        DiamondPrinterApplication.processInput(System.in, new DiamondPrinter());

        String expectedOutput = "Enter a character (A, B, C, etc.), or type 'exit' to quit: \n" +
                "Diamond for 'B':\n" +
                " A\n" +
                "B B\n" +
                " A\n" +
                "Enter a character (A, B, C, etc.), or type 'exit' to quit: \n" +
                "Program end.\n";

        assertEquals(removeWhitespace(expectedOutput), removeWhitespace(testOut.toString()));
    }

    @Test
    public void testProcessInputFailure() {
        String input = "123\nexit\n"; // Invalid input character followed by 'exit'
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        DiamondPrinterApplication.processInput(System.in, new DiamondPrinter());

        String expectedOutput = "Enter a character (A, B, C, etc.), or type 'exit' to quit: \n" +
                "Invalid input. Please enter a single character (A, B, C, etc.).\n" +
                "Enter a character (A, B, C, etc.), or type 'exit' to quit: \n" +
                "Program end.\n";

        assertEquals(removeWhitespace(expectedOutput), removeWhitespace(testOut.toString()));
    }

    private String removeWhitespace(String str) {
        return str.replaceAll("\\s", ""); // Remove all whitespace characters
    }
}
