package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;
import model.Grid;

public class RotateTetrominoCommand implements CommandInterface {
    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic logic;

    public RotateTetrominoCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.logic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.rotateClockwise();
        if (logic.hasCollided(grid, tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
