package cz.sparko.aoc2016.d8;

class Command {
    private final Action action;

    Command(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
}
