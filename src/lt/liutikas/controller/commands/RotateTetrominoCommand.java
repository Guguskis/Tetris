package lt.liutikas.controller.commands;

import lt.liutikas.controller.GameLogic;
import lt.liutikas.controller.TetrominoConveyor;

public class RotateTetrominoCommand implements Command {
    private final TetrominoConveyor conveyor;
    private final GameLogic logic;

    public RotateTetrominoCommand(TetrominoConveyor conveyor, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.logic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        logic.rotateClockwise(tetromino);
        if (logic.hasCollidedWithGrid(tetromino)) {
            logic.rotateCounterClockwise(tetromino);
        }
    }
}
