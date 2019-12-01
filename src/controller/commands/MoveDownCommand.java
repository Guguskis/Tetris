package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;
import model.tetromino.Tetromino;

public class MoveDownCommand implements Command {
    private final TetrominoConveyor conveyor;
    private final GameLogic logic;

    public MoveDownCommand(TetrominoConveyor conveyor, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.logic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();
        tetromino.moveDown();
        handleCollisionAndGameOver(tetromino);
    }

    private void handleCollisionAndGameOver(Tetromino tetromino) {
        if (logic.hasCollidedWithGrid(tetromino)) {
            tetromino.moveUp();
            removeFilledLines(tetromino);
            ifCannotSpawnNextSetGameOver();
            conveyor.move();
        }
    }

    private void ifCannotSpawnNextSetGameOver() {
        if (logic.hasCollidedWithGrid(conveyor.getNext())) {
            logic.setGameOver(true);
        }
    }

    private void removeFilledLines(Tetromino tetromino) {
        logic.imprintTetromino(tetromino);
        logic.removeFilledLines();
    }

}
