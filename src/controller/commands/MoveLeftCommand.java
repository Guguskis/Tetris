package controller.commands;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoFactory;

public class MoveLeftCommand implements CommandInterface {
    private final TetrominoFactory tetrominoFactory;
    private final Grid grid;
    private final GameLogic gameLogic;

    public MoveLeftCommand(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        this.tetrominoFactory = factory;
        this.grid = grid;
        this.gameLogic = gameLogic;
    }

    @Override
    public void execute() {
        var tetromino = tetrominoFactory.peekCurrent();

        tetromino.position.moveLeft();
        if (gameLogic.hasCollided(grid, tetromino)) {
            tetromino.position.moveRight();
        }
    }
}
