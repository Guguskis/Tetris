package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class MoveDownCommand implements CommandInterface {

    private final TetrominoConveyor tetrominoManager;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveDownCommand(TetrominoConveyor factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoManager = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoManager.getCurrent();

        tetromino.moveDown();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveUp();

            gameLogic.imprintTetromino(grid, tetromino);
            gameLogic.removeFilledLines(grid);

            if (gameLogic.hasCollided(grid, tetrominoManager.getNext())) {
                gameLogic.setGameOver();
            } else {
                tetrominoManager.move();
            }
        }


    }
}
