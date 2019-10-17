package controller.commands;

import model.Grid;
import controller.Rules;
import model.tetromino.Tetromino;

public class RotateTetrominoCommand implements CommandInterface {
    private final Tetromino tetromino;
    private final Grid grid;
    private final Rules rules;

    public RotateTetrominoCommand(Tetromino tetromino, Grid grid) {
        this.tetromino = tetromino;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        tetromino.rotateClockwise();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
