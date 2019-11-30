package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class RotateTetrominoCommand implements CommandInterface {
    private final TetrominoConveyor tetrominoManager;
    private final Grid grid;
    private final GameLogic gameLogic;

    public RotateTetrominoCommand(TetrominoConveyor factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoManager = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoManager.getCurrent();

        tetromino.rotateClockwise();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
