package com.diamondprinter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DiamondPrinterIntegrationTest {
    @Autowired
    private DiamondPrinter diamondPrinter;

    @Test
    public void testPrintDiamondIntegrationSuccess() {
        char midChar = 'C'; // Valid input character
        String expectedDiamond = "  A  \n B B \nC   C\n B B \n  A  \n";
        String actualDiamond = diamondPrinter.printDiamond(midChar);
        assertEquals(expectedDiamond, actualDiamond);
    }

    @Test
    public void testPrintDiamondIntegrationFail() {
        char midChar = 'a'; // Invalid input character
        try {
            diamondPrinter.printDiamond(midChar);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid character: a", e.getMessage());
        }
    }
}
