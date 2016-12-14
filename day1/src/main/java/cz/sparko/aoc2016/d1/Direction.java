package cz.sparko.aoc2016.d1;

public enum Direction {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int no;

    Direction(int no) {
        this.no = no;
    }

    private Direction byNo(int no) {
        switch (no) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                throw new IllegalArgumentException("unknown value [" + no + "]");
        }
    }

    public Direction next() {
        return byNo((no + 1) % 4);
    }

    public Direction prev() {
        return no == 0 ? byNo(3) : byNo(no - 1);
    }
}
