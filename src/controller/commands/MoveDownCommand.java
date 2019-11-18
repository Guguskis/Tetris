package controller.commands;

import controller.Rules;
import model.Grid;
import model.tetromino.TetrominoFactory;

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
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.position.moveDown();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveUp();

            rules.imprintTetromino(grid, tetromino);
            rules.removeFilledLines(grid);

            var newX = (grid.getWidth() - tetrominoFactory.peekCurrent().getWidth()) / 2;
            if (rules.hasCollided(grid, tetrominoFactory.peekNext())) {
                rules.setGameOver();
            } else {
                tetrominoFactory.moveConveyor();
            }
        }


    }
}
