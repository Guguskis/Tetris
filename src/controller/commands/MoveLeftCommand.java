package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class MoveLeftCommand implements CommandInterface {
    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveLeftCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveLeft();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveRight();
        }
    }
}
