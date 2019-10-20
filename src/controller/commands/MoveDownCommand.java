package controller.commands;

import model.Grid;
import controller.Rules;
import model.TetrominoFactory;

public class MoveDownCommand implements CommandInterface {

    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final Rules rules;

    public MoveDownCommand(TetrominoFactory factory, Grid grid, Rules rules) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.rules = rules;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.getCurrent();

        tetromino.position.moveDown();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveUp();

            rules.imprintTetromino(grid, tetromino);
            rules.removeFilledLines(grid);

            var newX = (grid.getWidth() - tetrominoFactory.getCurrent().getWidth()) / 2;
            tetrominoFactory.moveConveyor();
        }
    }
}
