package controller.commands;

import model.Grid;
import controller.Rules;
import model.tetromino.Tetromino;

public class MoveDownCommand implements CommandInterface {

    private final Tetromino tetromino;
    private final Grid grid;
    private final Rules rules;

    public MoveDownCommand(Tetromino tetromino, Grid grid) {
        this.tetromino = tetromino;
        this.grid = grid;
        this.rules = Rules.getInstance();
    }

    @Override
    public void execute() {
        tetromino.position.moveDown();
        if (rules.hasCollided(grid, tetromino)) {
            tetromino.position.moveUp();
            rules.imprintTetromino(grid, tetromino);
            rules.removeFilledLines(grid);
            tetromino.reset(0, 0);
        }
    }
}
