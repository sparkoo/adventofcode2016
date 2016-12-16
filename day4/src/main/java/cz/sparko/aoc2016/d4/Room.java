package cz.sparko.aoc2016.d4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

class Room {
    private final String name;
    private final int code;
    private final String checksum;

    Room(String name, int code, String checksum) {
        this.name = name;
        this.code = code;
        this.checksum = checksum;
    }

    static Room parseFromLine(String line) {
        List<String> splittedLine = Arrays.asList(line.split("-"));

        String name = splittedLine.stream()
                .limit(splittedLine.size() - 1)
                .reduce((a, b) -> a + b)
                .get();

        String[] codeAndChecksum = splittedLine.get(splittedLine.size() - 1).split("\\[");
        int code = Integer.valueOf(codeAndChecksum[0]);
        String checksum = codeAndChecksum[1].substring(0, codeAndChecksum[1].length() - 1);
        return new Room(name, code, checksum);
    }

    int getCode() {
        return code;
    }
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", checksum='" + checksum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return code == room.code &&
                Objects.equals(name, room.name) &&
                Objects.equals(checksum, room.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, checksum);
    }

    public String getChecksum() {
        return checksum;
    }
}
