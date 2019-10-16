package controller.commands;

import model.Grid;
import controller.Rules;
import model.Shape;

public class RotateShapeCommand implements CommandInterface {
    private final Shape shape;
    private final Grid grid;
    private final Rules rules;

    public RotateShapeCommand(Shape shape, Grid grid) {
        this.shape = shape;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        shape.rotateClockwise();
        if (rules.hasCollided(grid, shape)) {
            shape.rotateCounterClockwise();
        }
    }
}
