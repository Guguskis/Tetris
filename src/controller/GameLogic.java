package controller;

import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;

public class GameLogic {
    private boolean gameOver = false;
    private int goal = 0;

    private Grid grid;
    private ScoreKeeper scoreKeeper;
    private CollisionDetector collisionDetector;

    public GameLogic(Grid grid, ScoreKeeper scoreKeeper, CollisionDetector collisionDetector) {
        this.grid = grid;
        this.scoreKeeper = scoreKeeper;
        this.collisionDetector = collisionDetector;
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

    public void removeFilledLines() {
        var linesRemoved = 0;

        for (int y = grid.getHeight() - 1; y >= 0; ) {
            if (grid.lineIsEmpty(y)) {
                grid.removeLine(y);
                pushDownLinesAbove(y);

                linesRemoved++;
            } else {
                y--;
            }
        }

        increaseGoal(linesRemoved);
        scoreKeeper.add(linesRemoved, getLevel());
    }

    private void pushDownLinesAbove(int y) {
        for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
            grid.pushLineDown(lineAbove);
        }
    }

    private void increaseGoal(int linesRemoved) {
        switch (linesRemoved) {
            case 1:
                goal += 1;
                break;
            case 2:
                goal += 3;
                break;
            case 3:
                goal += 5;
                break;
            case 4:
                goal += 8;
                break;
        }
    }

    public int getLevel() {
        return goal / 10 + 1;
    }

    public int getScore() {
        return scoreKeeper.getScore();
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
}
