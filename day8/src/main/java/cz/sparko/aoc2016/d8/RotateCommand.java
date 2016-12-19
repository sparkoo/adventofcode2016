package cz.sparko.aoc2016.d8;

class RotateCommand extends Command {
    private final int position;
    private final int by;

    RotateCommand(Action action, int position, int by) {
        super(action);
        this.position = position;
        this.by = by;
    }

    public int getPosition() {
        return position;
    }

    public int getBy() {
        return by;
    }
}
