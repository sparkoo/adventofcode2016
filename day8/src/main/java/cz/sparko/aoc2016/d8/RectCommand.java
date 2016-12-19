package cz.sparko.aoc2016.d8;

class RectCommand extends Command {
    private final int x;
    private final int y;

    RectCommand(Action action, int x, int y) {
        super(action);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
