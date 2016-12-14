package cz.sparko.aoc2016.d1;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class DirectionTest {
    @Test
    public void givenDirection_whenNext_thenCorrectNextDirection() {
        assertEquals(Direction.NORTH.next(), Direction.EAST);
        assertEquals(Direction.EAST.next(), Direction.SOUTH);
        assertEquals(Direction.SOUTH.next(), Direction.WEST);
        assertEquals(Direction.WEST.next(), Direction.NORTH);
    }

    @Test
    public void givenDirection_whenPrev_thenCorrectNextDirection() {
        assertEquals(Direction.NORTH.prev(), Direction.WEST);
        assertEquals(Direction.WEST.prev(), Direction.SOUTH);
        assertEquals(Direction.SOUTH.prev(), Direction.EAST);
        assertEquals(Direction.EAST.prev(), Direction.NORTH);
    }
}