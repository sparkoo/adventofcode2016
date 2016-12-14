package cz.sparko.aoc2016.d1;

import java.util.Objects;

class Movement {
    private final Side side;
    private final int distance;

    Movement(Side side, int distance) {
        this.side = side;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "side=" + side +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return distance == movement.distance &&
                side == movement.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, distance);
    }

    Side getSide() {
        return side;
    }

    int getDistance() {
        return distance;
    }
}
