package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoFactory;

public class MoveDownCommand implements CommandInterface {

    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveDownCommand(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.position.moveDown();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.position.moveUp();

            gameLogic.imprintTetromino(grid, tetromino);
            gameLogic.removeFilledLines(grid);

            var newX = (grid.getWidth() - tetrominoFactory.peekCurrent().getWidth()) / 2;
            if (gameLogic.hasCollided(grid, tetrominoFactory.peekNext())) {
                gameLogic.setGameOver();
            } else {
                tetrominoFactory.moveConveyor();
            }
        }


    }
}
