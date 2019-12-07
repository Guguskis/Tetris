package lt.liutikas.controller.commands;

import lt.liutikas.controller.CollisionDetector;
import lt.liutikas.controller.GameLogic;
import lt.liutikas.controller.TetrominoConveyor;
import lt.liutikas.model.Grid;
import lt.liutikas.model.tetromino.Tetromino;

public class MoveDownCommand implements Command {
    private final Grid grid;
    private final GameLogic logic;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveDownCommand(Grid grid, GameLogic gameLogic, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.grid = grid;
        this.logic = gameLogic;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();
        tetromino.moveDown();
        handleCollisionAndGameOver(tetromino);
    }

    private void handleCollisionAndGameOver(Tetromino tetromino) {
        if (hasCollided(tetromino)) {
            tetromino.moveUp();
            removeFilledLines(tetromino);
            ifCannotSpawnNextSetGameOver();
            conveyor.move();
        }
    }

    private boolean hasCollided(Tetromino tetromino) {
        return detector.hasCollided(grid, tetromino);
    }

    private void ifCannotSpawnNextSetGameOver() {
        if (hasCollided(conveyor.getNext())) {
            logic.setGameOver(true);
        }
    }

    private void removeFilledLines(Tetromino tetromino) {
        logic.imprintTetromino(tetromino);
        logic.removeFilledLines();
    }

}
