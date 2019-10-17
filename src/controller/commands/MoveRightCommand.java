package controller.commands;

import model.Grid;
import controller.Rules;
import model.tetromino.Tetromino;

public class MoveRightCommand implements CommandInterface {

    private final Tetromino tetromino;
    private final Grid grid;
    private final Rules rules;

    public MoveRightCommand(Tetromino tetromino, Grid grid) {
        this.tetromino = tetromino;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        tetromino.position.moveRight();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveLeft();
        }
    }
}
