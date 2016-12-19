package cz.sparko.aoc2016.d8;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DisplayTest {
    @Test
    public void given_when_then() {
        Display d = new Display(7, 3);

        d.rect(3, 2);
        boolean[][] expectedAfterRect1 = new boolean[][]{
                {true, true, true, false, false, false, false},
                {true, true, true, false, false, false, false},
                {false, false, false, false, false, false, false}
        };
        System.out.println(d.toString());
        assertTwoDimensionArray(d.getField(), expectedAfterRect1);


        d.rotateColumn(1, 1);
        boolean[][] expectedAfterRotateCol1 = new boolean[][]{
                {true, false, true, false, false, false, false},
                {true, true, true, false, false, false, false},
                {false, true, false, false, false, false, false}
        };
        System.out.println(d.toString());
        assertTwoDimensionArray(d.getField(), expectedAfterRotateCol1);

        d.rotateRow(0, 4);
        boolean[][] expectedAfterRotateRow1 = new boolean[][]{
                {false, false, false, false, true, false, true},
                {true, true, true, false, false, false, false},
                {false, true, false, false, false, false, false}
        };
        System.out.println(d.toString());
        assertTwoDimensionArray(d.getField(), expectedAfterRotateRow1);

        d.rotateColumn(1, 1);
        boolean[][] expectedAfterRotateCol2 = new boolean[][]{
                {false, true, false, false, true, false, true},
                {true, false, true, false, false, false, false},
                {false, true, false, false, false, false, false}
        };
        System.out.println(d.toString());
        assertTwoDimensionArray(d.getField(), expectedAfterRotateCol2);
    }


    private void assertTwoDimensionArray(boolean[][] realField, boolean[][] expectedField) {
        assertEquals(realField.length, expectedField.length);
        for (int i = 0; i < expectedField.length; i++) {
            assertEquals(realField[i].length, expectedField[i].length);
            for (int j = 0; j < expectedField[i].length; j++) {
                assertEquals(realField[i][j], expectedField[i][j]);
            }
        }
    }
}