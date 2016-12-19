package cz.sparko.aoc2016.d8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.IntStream.range;

class Display {
    private final int x;
    private final int y;
    private final boolean[][] field;

    Display(int x, int y) {
        this.x = x;
        this.y = y;
        this.field = new boolean[y][x];
    }

    void rect(int x, int y) {
        range(0, y).forEach(i -> range(0, x)
                .forEach(j -> field[i][j] = true));
    }

    private void shiftRow(int row) {
        swap(row, 0, row, x - 1);
        for (int i = x - 1; i > 1; i--) {
            swap(row, i, row, i - 1);
        }
    }

    private void shiftCol(int col) {
        swap(0, col, y - 1, col);
        for (int i = y - 1; i > 1; i--) {
            swap(i, col, i - 1, col);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean tmp;
        tmp = field[x1][y1];
        field[x1][y1] = field[x2][y2];
        field[x2][y2] = tmp;
    }

    void rotateRow(int x, int by) {
        for (int i = 0; i < by; i++) {
            shiftRow(x);
        }
    }

    void rotateColumn(int y, int by) {
        for (int i = 0; i < by; i++) {
            shiftCol(y);
        }
    }

    boolean[][] getField() {
        return field;
    }

    void doCommand(Command command) {
        switch (command.getAction()) {
            case RECT:
                if (command instanceof RectCommand) {
                    RectCommand rectCommand = (RectCommand) command;
                    rect(rectCommand.getX(), rectCommand.getY());
                } else {
                    throw new IllegalArgumentException("invalid command");
                }
                break;
            case ROTATE_COL:
                if (command instanceof RotateCommand) {
                    RotateCommand rotateCommand = (RotateCommand) command;
                    rotateColumn(rotateCommand.getPosition(), rotateCommand.getBy());
                } else {
                    throw new IllegalArgumentException("invalid command");
                }
                break;
            case ROTATE_ROW:
                if (command instanceof RotateCommand) {
                    RotateCommand rotateCommand = (RotateCommand) command;
                    rotateRow(rotateCommand.getPosition(), rotateCommand.getBy());
                } else {
                    throw new IllegalArgumentException("invalid command");
                }
                break;
        }
    }

    void doCommands(List<Command> commandList) {
        commandList.forEach(this::doCommand);
    }

    int enabledSwitchesCount() {
        int counter = 0;
        for (boolean[] aField : field) {
            for (boolean anAField : aField) {
                if (anAField)
                    counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringField = new StringBuilder();
        for (boolean[] aField : field) {
            for (boolean anAField : aField) {
                stringField.append(anAField ? "#" : ".");
            }
            stringField.append("\n");
        }
        return stringField.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Display display = (Display) o;
        return Arrays.equals(field, display.field);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(field);
    }
}
