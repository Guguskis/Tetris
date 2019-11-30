package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;

public class RotateTetrominoCommand implements CommandInterface {
    private final TetrominoConveyor conveyor;
    private final GameLogic logic;

    public RotateTetrominoCommand(TetrominoConveyor conveyor, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.logic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.rotateClockwise();
        if (logic.hasCollided(tetromino)) {
            tetromino.rotateCounterClockwise();
        }
    }
}
