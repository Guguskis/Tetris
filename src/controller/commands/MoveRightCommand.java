package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;
import model.Grid;

public class MoveRightCommand implements CommandInterface {

    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveRightCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveRight();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveLeft();
        }
    }
}
