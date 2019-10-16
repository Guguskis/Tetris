package commands;

import ds.Grid;
import ds.Rules;
import ds.Shape;

public class MoveDownCommand implements CommandInterface {

    private final Shape shape;
    private final Grid grid;
    private final Rules rules;

    public MoveDownCommand(Shape shape, Grid grid) {
        this.shape = shape;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        shape.position.moveDown();
        if (rules.hasCollided(grid, shape)) {
            shape.position.moveUp();
            rules.imprintShape(grid, shape);
            shape.reset(0, 0);
            rules.removeFilledLines(grid);
        }
    }
}
