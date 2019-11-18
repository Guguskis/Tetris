package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoFactory;

public class MoveRightCommand implements CommandInterface {

    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveRightCommand(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.position.moveRight();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.position.moveLeft();
        }
    }
}
