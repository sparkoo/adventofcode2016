package cz.sparko.aoc2016.d2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cz.sparko.aoc2016.d2.Direction.DOWN;
import static cz.sparko.aoc2016.d2.Direction.LEFT;
import static cz.sparko.aoc2016.d2.Direction.RIGHT;
import static cz.sparko.aoc2016.d2.Direction.UP;

public class KeypadFollower {
    public static void main(String[] args) throws IOException {
        new KeypadFollower().follow("input.txt");
    }

    private void follow(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<String> lines;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            lines = stream
                    .collect(Collectors.toList());
        }

        StringBuilder code = new StringBuilder();
        Keypad keypad = new Keypad(5);

        lines.forEach(line -> {
            Stream.of(line.split(""))
                    .map(this::parseDirections)
                    .forEach(keypad::move);
            code.append(keypad.getKey());
        });

        System.out.println(code.toString());
    }

    private Direction parseDirections(String direction) {
        switch (direction) {
            case "D":
                return DOWN;
            case "U":
                return UP;
            case "L":
                return LEFT;
            case "R":
                return RIGHT;
            default:
                throw new IllegalArgumentException("Invalid direction char [" + direction + "]");
        }
    }
}
