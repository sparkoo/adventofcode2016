package cz.sparko.aoc2016.d4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RoomValidator {
    public static void main(String[] args) throws IOException {
        new RoomValidator().validate("input.txt");
    }

    private void validate(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<String> rooms;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            long code = stream
                    .map(Room::parseFromLine)
                    .filter(this::isValid)
                    .map(Room::getCode)
                    .reduce((result, r) -> result + r)
                    .get();
            System.out.println(code);
        }
    }

    boolean isValid(Room room) {
        Map<String, LetterCountPair> letterCountsMap = new HashMap<>();
        List<LetterCountPair> letterCounts = new ArrayList<>();
        Stream.of(room.getName().split(""))
                .forEach(l -> {
                    if (letterCountsMap.containsKey(l)) {
                        letterCountsMap.get(l).add();
                    } else {
                        LetterCountPair pair = new LetterCountPair(l);
                        letterCountsMap.put(l, pair);
                        letterCounts.add(pair);
                    }
                });

        String expectedChecksum = letterCounts.stream()
                .sorted((o1, o2) -> {
                    if (o1.getCount() != o2.getCount()) {
                        return o2.getCount() - o1.getCount();
                    } else {
                        return o1.getLetter().compareTo(o2.getLetter());
                    }
                })
                .limit(5)
                .map(LetterCountPair::getLetter)
                .reduce((r, l) -> r + l)
                .get();
        return expectedChecksum.equals(room.getChecksum());
    }


    private static class LetterCountPair {
        private final String letter;

        private int count;
        LetterCountPair(String letter) {
            this.letter = letter;
            this.count = 1;
        }

        @Override
        public String toString() {
            return "LetterCountPair{" +
                    "letter='" + letter + '\'' +
                    ", count=" + count +
                    '}';
        }

        void add() {
            count++;
        }

        public String getLetter() {
            return letter;
        }

        int getCount() {
            return count;
        }
    }
}
