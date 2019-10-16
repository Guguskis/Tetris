package controller.commands;

import model.Grid;
import controller.Rules;
import model.Shape;

public class MoveLeftCommand implements CommandInterface {
    private final Shape shape;
    private final Grid grid;
    private final Rules rules;

    public MoveLeftCommand(Shape shape, Grid grid) {
        this.shape = shape;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        shape.position.moveLeft();
        if (rules.hasCollided(grid, shape)) {
            shape.position.moveRight();
        }
    }
}
