package cz.sparko.aoc2016.d1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grid {
    private final Map<Point, PointPosition> points;

    Grid() {
        points = new HashMap<>();
    }

    void placePoint(PointPosition pointPosition) {
        points.put(pointPosition.getPoint(), pointPosition);
    }

    void movePoint(String pointName, Movement movement) {
        points.get(pointName).move(movement);
    }

    void movePoint(final Point point, List<Movement> movements) {
        movements.forEach(m -> points.get(point).move(m));
    }

    PointPosition getPointPosition(Point point) {
        return points.get(point);
    }

    int distance(Point p1, Point p2) {
        PointPosition position1 = points.get(p1);
        PointPosition position2 = points.get(p2);

        return Math.abs(position1.getX() - position2.getX()) + Math.abs(position1.getY() - position2.getY());
    }
}
