package controller.commands;

import controller.Rules;
import model.Grid;
import model.tetromino.TetrominoFactory;

public class RotateTetrominoCommand implements CommandInterface {
    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final Rules rules;

    public RotateTetrominoCommand(TetrominoFactory factory, Grid grid, Rules rules) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.rules = rules;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.rotateClockwise();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
