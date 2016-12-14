package cz.sparko.aoc2016.d1;

public enum Side {
    LEFT, RIGHT;

    public static Side ofValue(String val) {
        if (val.equals("L")) {
            return LEFT;
        }
        if (val.equals("R")) {
            return RIGHT;
        }

        throw new IllegalArgumentException("Invalid value [" + val + "]");
    }
}
