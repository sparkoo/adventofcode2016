package cz.sparko.aoc2016.d1;

import java.util.Objects;

class PointPosition {
    private Point point;
    private int x;
    private int y;
    private Direction direction;

    PointPosition(Point point, int x, int y, Direction direction) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    private void turn(Side side) {
        if (side == Side.LEFT) {
            direction = direction.prev();
        } else {
            direction = direction.next();
        }
    }

    private void move(int distance) {
        switch (direction) {
            case NORTH:
                y += distance;
                break;
            case EAST:
                x += distance;
                break;
            case SOUTH:
                y -= distance;
                break;
            case WEST:
                x -= distance;
                break;
        }
    }

    void move(Movement movement) {
        turn(movement.getSide());
        move(movement.getDistance());
    }

    @Override
    public String toString() {
        return "PointPosition{" +
                "point=" + point +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointPosition that = (PointPosition) o;
        return x == that.x &&
                y == that.y &&
                Objects.equals(point, that.point) &&
                direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, x, y, direction);
    }

    public Point getPoint() {
        return point;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
