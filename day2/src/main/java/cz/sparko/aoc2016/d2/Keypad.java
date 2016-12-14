package cz.sparko.aoc2016.d2;

import java.util.List;

class Keypad {
    private int key;

    Keypad(int key) {
        this.key = key;
    }

    void move(List<Direction> directions) {
        directions.forEach(this::move);
    }

    void move(Direction direction) {
        switch (direction) {
            case UP:
                if (key - 3 >= 1) {
                    key -= 3;
                }
                break;
            case DOWN:
                if (key + 3 <= 9) {
                    key += 3;
                }
                break;
            case LEFT:
                if ((key - 1) % 3 > 0) {
                    key--;
                }
                break;
            case RIGHT:
                if ((key) % 3 > 0) {
                    key++;
                }
                break;
        }
    }

    public int getKey() {
        return key;
    }
}
