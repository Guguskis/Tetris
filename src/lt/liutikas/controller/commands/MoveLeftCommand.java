package lt.liutikas.controller.commands;

import lt.liutikas.controller.GameLogic;
import lt.liutikas.controller.TetrominoConveyor;

public class MoveLeftCommand implements Command {
    private final TetrominoConveyor conveyor;
    private final GameLogic logic;

    public MoveLeftCommand(TetrominoConveyor conveyor, GameLogic logic) {
        this.conveyor = conveyor;
        this.logic = logic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveLeft();
        if (logic.hasCollidedWithGrid(tetromino)) {
            tetromino.moveRight();
        }
    }
}
