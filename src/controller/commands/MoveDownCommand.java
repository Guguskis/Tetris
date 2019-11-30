package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public class MoveDownCommand implements CommandInterface {

    private final TetrominoConveyor conveyor;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveDownCommand(TetrominoConveyor conveyor, Grid grid, GameLogic gameLogic) {
        this.conveyor = conveyor;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveDown();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.moveUp();

            gameLogic.imprintTetromino(grid, tetromino);
            gameLogic.removeFilledLines(grid);

            if (gameLogic.hasCollided(grid, conveyor.getNext())) {
                gameLogic.setGameOver();
            } else {
                conveyor.move();
            }
        }


    }
}
