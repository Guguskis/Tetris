package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class RotateTetrominoCommand implements CommandInterface {
    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic gameLogic;

    public RotateTetrominoCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.rotateClockwise();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
