package lt.liutikas.controller.commands;

import lt.liutikas.controller.CollisionDetector;
import lt.liutikas.controller.GameState;
import lt.liutikas.controller.TetrominoConveyor;
import lt.liutikas.model.Grid;
import lt.liutikas.model.tetromino.Tetromino;

public class MoveDownCommand implements Command {
    private final Grid grid;
    private final GameState gameState;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveDownCommand(Grid grid, GameState gameState, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.grid = grid;
        this.gameState = gameState;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        if (!gameState.isGameOver()) {
            Tetromino tetromino = conveyor.getCurrent();
            tetromino.moveDown();
            handleCollisionAndGameOver(tetromino);
        }
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
            gameState.setGameOver(true);
        }
    }

    private void removeFilledLines(Tetromino tetromino) {
        int removedLines = grid.getFullLineCount(tetromino);
        gameState.update(removedLines);
        grid.removeFullLines(tetromino);
    }

}
