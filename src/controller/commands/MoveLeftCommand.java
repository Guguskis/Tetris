package controller.commands;

import model.Grid;
import controller.Rules;
import model.TetrominoFactory;

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
        var tetromino = tetrominoFactory.getCurrent();

        tetromino.position.moveLeft();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveRight();
        }
    }
}
