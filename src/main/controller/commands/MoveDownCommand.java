package main.controller.commands;

import main.model.GameState;
import main.model.Playfield;
import main.model.tetromino.Tetromino;
import main.service.CollisionDetector;
import main.service.TetrominoConveyor;

public class MoveDownCommand implements Command {
    private final Playfield playfield;
    private final GameState gameState;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveDownCommand(Playfield playfield, GameState gameState, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.playfield = playfield;
        this.gameState = gameState;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        Tetromino tetromino = conveyor.getCurrent();
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
        return detector.hasCollided(playfield, tetromino);
    }

    private void ifCannotSpawnNextSetGameOver() {
        if (hasCollided(conveyor.getNext())) {
            gameState.setGameOver(true);
        }
    }

    private void removeFilledLines(Tetromino tetromino) {
        int removedLines = playfield.getFullLineCount(tetromino);
        playfield.removeFullLines(tetromino);
        gameState.update(removedLines);
    }

}
