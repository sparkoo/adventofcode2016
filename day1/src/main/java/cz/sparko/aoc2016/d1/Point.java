package cz.sparko.aoc2016.d1;

import java.util.Objects;

class Point {
    private final String name;

    public Point(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(name, point.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
