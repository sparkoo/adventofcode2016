package cz.sparko.aoc2016.d1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static cz.sparko.aoc2016.d1.Direction.NORTH;
import static cz.sparko.aoc2016.d1.Direction.SOUTH;
import static cz.sparko.aoc2016.d1.Direction.WEST;
import static cz.sparko.aoc2016.d1.Side.LEFT;
import static cz.sparko.aoc2016.d1.Side.RIGHT;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

@Test
public class PointPositionTest {

    private Point p1;
    private Point p2;
    private Grid grid;

    @BeforeClass
    public void setUpClass() {
        p1 = new Point("p1");
        p2 = new Point("p2");
        grid = new Grid();
    }

    @DataProvider
    public Object[][] movements() {
        return new Object[][]{
                {
                        new PointPosition(p1, 10, 10, NORTH),
                        asList(new Movement(LEFT, 5), new Movement(LEFT, 5)),
                        new PointPosition(p1, 5, 5, SOUTH)
                },
                {
                        new PointPosition(p1, 10, 10, SOUTH),
                        Collections.singletonList(new Movement(RIGHT, 15)),
                        new PointPosition(p1, -5, 10, WEST)
                },
                {
                        new PointPosition(p1, 10, 10, NORTH),
                        asList(new Movement(LEFT, 5), new Movement(LEFT, 5), new Movement(RIGHT, 7)),
                        new PointPosition(p1, -2, 5, WEST)
                }
        };
    }

    @Test(dataProvider = "movements")
    public void givenOnePointAndMovements_whenMove_thenPointIsAtRightPosition(PointPosition initialPosition,
                                                                              List<Movement> movements,
                                                                              PointPosition expectedPosition) {
        grid.placePoint(initialPosition);

        grid.movePoint(expectedPosition.getPoint(), movements);
        assertEquals(grid.getPointPosition(expectedPosition.getPoint()), expectedPosition);
    }
}