package com.diamondprinter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiamondPrinterTest {
    private final DiamondPrinter diamondPrinter = new DiamondPrinter();

    @Test
    public void testPrintDiamondSuccess() {
        char midChar = 'C'; // Valid input character
        String expectedDiamond = "  A  \n B B \nC   C\n B B \n  A  \n";

        String actualDiamond = diamondPrinter.printDiamond(midChar);

        assertEquals(expectedDiamond, actualDiamond);
    }

    @Test
    public void testPrintDiamondFailure() {
        char midChar = '1'; // Invalid input character

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            diamondPrinter.printDiamond(midChar);
        });

        assertEquals("Invalid character: 1", exception.getMessage());
    }
}
