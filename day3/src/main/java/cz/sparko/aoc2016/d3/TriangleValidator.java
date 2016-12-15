package cz.sparko.aoc2016.d3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleValidator {
    boolean isValid(Triangle triangle) {
        List<Integer> sides = Stream.of(triangle.getSideA(), triangle.getSideB(), triangle.getSideC()).sorted()
                .collect(Collectors.toList());
        int min = sides.get(0);
        int mid = sides.get(1);
        int max = sides.get(2);

        return min + mid > max;
    }

    private int getMin(Triangle triangle) {
        return Stream.of(triangle.getSideA(), triangle.getSideB(), triangle.getSideC())
                .reduce((a, b) -> a > b ? b : a)
                .get();
    }
}
