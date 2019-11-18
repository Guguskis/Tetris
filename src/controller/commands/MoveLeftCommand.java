package controller.commands;

import controller.Rules;
import model.Grid;
import model.tetromino.TetrominoFactory;

public class MoveLeftCommand implements CommandInterface {
    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final Rules rules;

    public MoveLeftCommand(TetrominoFactory factory, Grid grid, Rules rules) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.rules = rules;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.position.moveLeft();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveRight();
        }
    }
}
