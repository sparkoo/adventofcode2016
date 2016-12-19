package cz.sparko.aoc2016.d8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisplayController {
    private static final Logger LOG = LoggerFactory.getLogger(DisplayController.class);

    public static void main(String[] args) throws IOException {
        long count = new DisplayController().control("input.txt");
        LOG.info("[{}] is switched on", count);
    }

    private long control(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<Command> commands;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            commands = stream.map(this::parseCommand).collect(Collectors.toList());
        }

        Display display = new Display(50, 6);
        display.doCommands(commands);
        System.out.println(display.toString());

        return display.enabledSwitchesCount();
    }

    Command parseCommand(String line) {
        String[] splittedLine = line.split(" ");
        switch (splittedLine[0]) {
            case "rect":
                return parseRectCommand(line);
            case "rotate":
                return parseRotateCommand(line);
            default:
                throw new IllegalArgumentException("invalid line [" + line + "]");
        }
    }

    Command parseRectCommand(String line) {
        String[] coords = line.split(" ")[1].split("x");
        return new RectCommand(Action.RECT, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }

    Command parseRotateCommand(String line) {
        String[] splitted = line.split(" ");
        String what = splitted[1];
        int position = Integer.parseInt(splitted[2].split("=")[1]);
        int by = Integer.parseInt(splitted[4]);
        final Action action;

        switch (what) {
            case "row":
                action = Action.ROTATE_ROW;
                break;
            case "column":
                action = Action.ROTATE_COL;
                break;
            default:
                throw new IllegalArgumentException("invalid line");
        }
        return new RotateCommand(action, position, by);
    }

}
