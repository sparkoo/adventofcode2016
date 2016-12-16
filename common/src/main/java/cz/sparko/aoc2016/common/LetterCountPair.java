package cz.sparko.aoc2016.common;

public class LetterCountPair {
    private final String letter;

    private int count;

    public LetterCountPair(String letter) {
        this.letter = letter;
        this.count = 1;
    }

    public LetterCountPair(String letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    @Override
    public String toString() {
        return "LetterCountPair{" +
                "letter='" + letter + '\'' +
                ", count=" + count +
                '}';
    }

    public void add() {
        count++;
    }

    public String getLetter() {
        return letter;
    }

    public int getCount() {
        return count;
    }
}
