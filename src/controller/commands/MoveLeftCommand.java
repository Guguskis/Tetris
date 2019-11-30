package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class MoveLeftCommand implements CommandInterface {
    private final TetrominoConveyor tetrominoManager;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveLeftCommand(TetrominoConveyor factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoManager = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoManager.getCurrent();

        tetromino.moveLeft();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveRight();
        }
    }
}
