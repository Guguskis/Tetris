package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoManager;

public class MoveRightCommand implements CommandInterface {

    private final TetrominoManager tetrominoManager;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveRightCommand(TetrominoManager factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoManager = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoManager.getCurrent();

        tetromino.moveRight();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveLeft();
        }
    }
}
