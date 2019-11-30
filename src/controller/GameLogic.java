package controller;

import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;

public class GameLogic {
    private ScoreCalculator scoreCalculator;
    private int goal = 0;
    private boolean gameOver = false;


    public GameLogic(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    public void imprintTetromino(Grid grid, Tetromino tetromino) {
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

    public boolean hasCollided(Grid grid, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var mappedCoordinates = tetromino.getPosition().plus(new Position(j, i));
                var isOverlapped = isOverlapped(grid, tetromino, mappedCoordinates);
                var isOutOfBounds = isOutOfBounds(grid, tetromino, mappedCoordinates);

                if (isOverlapped || isOutOfBounds) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlapped(Grid grid, Tetromino tetromino, Position coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && gridTile == Tile.OCCUPIED;
    }

    private boolean isOutOfBounds(Grid grid, Tetromino tetromino, Position coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && gridTile == Tile.OUT_OF_BOUNDS;
    }

    public void removeFilledLines(Grid grid) {
        var linesRemoved = 0;
        for (int y = grid.getHeight() - 1; y >= 0; ) {
            if (grid.lineIsEmpty(y)) {
                grid.removeLine(y);
                for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
                    grid.pushLineDown(lineAbove);
                }

                linesRemoved++;
            } else {
                y--;
            }
        }

        increaseGoal(linesRemoved);
        try {
            scoreCalculator.add(linesRemoved, getLevel());
        } catch (Exception e) {
            e.printStackTrace();
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
        return scoreCalculator.getScore();
    }

    public double getTickIntervalInMilliseconds() {
        return Math.pow((0.8 - ((getLevel() - 1) * 0.007)), getLevel() - 1);
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
