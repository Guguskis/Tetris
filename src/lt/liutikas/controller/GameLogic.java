package lt.liutikas.controller;

import lt.liutikas.controller.rotator.Rotator;
import lt.liutikas.model.Grid;
import lt.liutikas.model.Position;
import lt.liutikas.model.Tile;
import lt.liutikas.model.tetromino.Tetromino;

public class GameLogic {
    private boolean gameOver = false;

    private Grid grid;
    private Score score;
    private Level level;
    private CollisionDetector collisionDetector;
    private Rotator rotator;

    public GameLogic(Grid grid, Score score, Level level, CollisionDetector collisionDetector, Rotator rotator) {
        this.grid = grid;
        this.score = score;
        this.level = level;
        this.collisionDetector = collisionDetector;
        this.rotator = rotator;
    }

    public void imprintTetromino(Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var coordinates = new Position(j, i);
                var tile = tetromino.getUnmappedTile(coordinates);

                if (tile == Tile.OCCUPIED) {
                    var mappedCoordinates = coordinates.plus(tetromino.getPosition());
                    grid.setTile(mappedCoordinates, tile);
                }
            }
        }
    }

    public boolean hasCollidedWithGrid(Tetromino tetromino) {
        return collisionDetector.hasCollided(grid, tetromino);
    }

    // Todo move to grid, atleast do something about his
    public void removeFilledLines() {
        var linesRemoved = 0;

        for (int y = grid.getHeight() - 1; y >= 0; ) {
            if (grid.lineIsEmpty(y)) {
                grid.removeLine(y);
                grid.pushDownLinesAbove(y);
                linesRemoved++;
            } else {
                y--;
            }
        }

        level.increase(linesRemoved);
        score.increase(linesRemoved, getLevel());
    }


    public int getLevel() {
        return level.getLevel();
    }

    public int getScore() {
        return score.getScore();
    }

    public double getTickIntervalInMilliseconds() {
        return Math.pow((0.8 - ((getLevel() - 1) * 0.007)), getLevel() - 1);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void rotateClockwise(Tetromino tetromino) {
        rotator.clockwise(tetromino);
    }

    public void rotateCounterClockwise(Tetromino tetromino) {
        rotator.counterClockwise(tetromino);
    }
}
