package cz.sparko.aoc2016.d1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathFollower {

    public static void main(String[] args) throws IOException {
        new PathFollower().follow("input.txt");
    }

    private void follow(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<Movement> movements;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            movements = stream
                    .flatMap(line -> Stream.of(line.split(", ")))
                    .map(this::parseMovement)
                    .collect(Collectors.toList());
        }

        Grid grid = new Grid();
        Point beginning = new Point("beginning");
        Point end = new Point("end");
        grid.placePoint(new PointPosition(beginning, 0, 0, Direction.NORTH));
        grid.placePoint(new PointPosition(end, 0, 0, Direction.NORTH));

        grid.movePoint(end, movements);

        System.out.println(grid.getPointPosition(beginning));
        System.out.println(grid.getPointPosition(end));
        System.out.println(grid.distance(beginning, end));
    }

    private Movement parseMovement(String movement) {
        return new Movement(Side.ofValue(movement.substring(0, 1)), Integer.parseInt(movement.substring(1)));
    }
}
