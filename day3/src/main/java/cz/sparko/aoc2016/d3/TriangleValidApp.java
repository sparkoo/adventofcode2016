package cz.sparko.aoc2016.d3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleValidApp {
    public static void main(String[] args) throws IOException {
        new TriangleValidApp().validate("input.txt");
    }

    private void validate(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<Triangle> triangles;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            triangles = stream
                    .map(this::parseTriangle)
                    .collect(Collectors.toList());
        }

        TriangleValidator triangleValidator = new TriangleValidator();

        System.out.println(triangles.size());

        long validTriangles = triangles.stream()
                .filter(triangleValidator::isValid)
                .count();

        System.out.println(validTriangles);
    }

    private Triangle parseTriangle(String line) {
        List<Integer> sides = Arrays.stream(line.split(" "))
                .filter(no -> no.length() > 0)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return new Triangle(sides.get(0), sides.get(1), sides.get(2));
    }

}
