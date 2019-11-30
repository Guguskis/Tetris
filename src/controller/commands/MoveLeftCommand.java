package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;

public class MoveLeftCommand implements CommandInterface {
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
