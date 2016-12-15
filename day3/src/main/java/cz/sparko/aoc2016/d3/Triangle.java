package cz.sparko.aoc2016.d3;

import java.util.Objects;

class Triangle {
    private final int sideA;
    private final int sideB;
    private final int sideC;

    Triangle(int sideA, int sideB, int sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                ", sideC=" + sideC +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return sideA == triangle.sideA &&
                sideB == triangle.sideB &&
                sideC == triangle.sideC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    int getSideA() {
        return sideA;
    }

    int getSideB() {
        return sideB;
    }

    int getSideC() {
        return sideC;
    }
}
