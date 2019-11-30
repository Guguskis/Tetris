package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class MoveLeftCommand implements CommandInterface {
    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic logic;

    public MoveLeftCommand(TetrominoConveyor conveyor, Grid grid, GameLogic logic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.logic = logic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveLeft();
        if (logic.hasCollided(grid, tetromino)) {
            tetromino.moveRight();
        }
    }
}
