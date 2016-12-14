package cz.sparko.aoc2016.d2;

import org.testng.annotations.Test;

import static cz.sparko.aoc2016.d2.Direction.DOWN;
import static cz.sparko.aoc2016.d2.Direction.LEFT;
import static cz.sparko.aoc2016.d2.Direction.RIGHT;
import static cz.sparko.aoc2016.d2.Direction.UP;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

@Test
public class KeypadTest {
    @Test
    public void givenInputFromExample_whenProcess_thenResultShouldBe1985() {
        StringBuilder code = new StringBuilder();

        Keypad keypad = new Keypad(5);
        keypad.move(asList(UP, LEFT, LEFT));
        code.append(keypad.getKey());

        keypad.move(asList(RIGHT, RIGHT, DOWN, DOWN, DOWN));
        code.append(keypad.getKey());

        keypad.move(asList(LEFT, UP, RIGHT, DOWN, LEFT));
        code.append(keypad.getKey());

        keypad.move(asList(UP, UP, UP, UP, DOWN));
        code.append(keypad.getKey());

        assertEquals(code.toString(), "1985");
    }
}