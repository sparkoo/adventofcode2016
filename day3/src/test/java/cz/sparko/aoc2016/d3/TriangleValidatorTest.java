package cz.sparko.aoc2016.d3;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TriangleValidatorTest {

    TriangleValidator triangleValidator;

    @BeforeClass
    public void setUp() {
        triangleValidator = new TriangleValidator();
    }

    @DataProvider
    public Object[][] triangles() {
        return new Object[][] {
                {new Triangle(1,2,3), false},
                {new Triangle(3,2,1), false},
                {new Triangle(3,1,2), false},
                {new Triangle(5,10,25), false},
                {new Triangle(5,25,10), false},
                {new Triangle(1,2,2), true},
                {new Triangle(15,17,20), true},
                {new Triangle(15,20,17), true},
                {new Triangle(20,17,15), true}
        };
    }

    @Test(dataProvider = "triangles")
    public void given_when_then(Triangle triangle, boolean expectValid) {
        assertEquals(triangleValidator.isValid(triangle), expectValid, triangle.toString());
    }

}