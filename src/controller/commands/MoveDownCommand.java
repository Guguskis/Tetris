package controller.commands;

import controller.GameLogic;
import controller.TetrominoConveyor;
import model.Grid;
import model.tetromino.Tetromino;

public class MoveDownCommand implements CommandInterface {

    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveDownCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        // Todo injetc dependencies
        this.conveyor = conveyor;
        this.grid = grid;
        this.gameLogic = gameLogic;
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

    private void ifCannotSpawnNextSetGameOver() {
        if (hasCollided(conveyor.getNext())) {
            gameLogic.setGameOver(true);
        }
    }

    private void removeFilledLines(Tetromino tetromino) {
        gameLogic.imprintTetromino(grid, tetromino);
        gameLogic.removeFilledLines(grid);
    }

    private boolean hasCollided(Tetromino tetromino) {
        return gameLogic.hasCollided(grid, tetromino);
    }

}
