package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;

public class MoveRightCommand implements CommandInterface {

    private final TetrominoConveyor conveyor;
    private final GameLogic logic;

    public MoveRightCommand(TetrominoConveyor conveyor, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.logic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveRight();
        // Todo new class CollisionDetector?
        if (logic.hasCollided(tetromino)) {
            tetromino.moveLeft();
        }
    }
}
