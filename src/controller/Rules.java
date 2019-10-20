package controller;

import model.Grid;
import model.Position;
import model.tetromino.Tetromino;
import model.Tile;

public class Rules {
    private static Rules instance;
    public ScoreCalculator scoreCalculator;
    private int goal = 0;

    public Rules(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    public void imprintTetromino(Grid grid, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var coordinates = new Position(j, i);
                var tile = tetromino.getUnmappedTile(coordinates);

                if (tile == Tile.Occupied) {
                    var mappedCoordinates = coordinates.plus(tetromino.position);
                    grid.setTile(mappedCoordinates, tile);
                }
            }
        }
    }

    public boolean hasCollided(Grid grid, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var mappedCoordinates = tetromino.position.plus(new Position(j, i));
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

        if (tetrominoTile == Tile.Occupied && gridTile == Tile.Occupied) {
            return true;
        }
        return false;
    }

    private boolean isOutOfBounds(Grid grid, Tetromino tetromino, Position coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        if (tetrominoTile == Tile.Occupied && gridTile == Tile.OutOfBounds) {
            return true;
        }
        return false;
    }

    public void removeFilledLines(Grid grid) {
        var linesRemoved = 0;
        for (int y = grid.getHeight() - 1; y >= 0; y--) {
            if (grid.lineIsEmpty(y)) {
                grid.removeLine(y);
                for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
                    grid.pushLineDown(lineAbove);
                }

                linesRemoved++;
                y++;
            }
        }

        increaseGoal(linesRemoved);
        scoreCalculator.calculate(linesRemoved, getLevel());
    }

    public static Rules getInstance() {
        if (instance == null) {
            instance = new Rules(new ScoreCalculator());
        }
        return instance;
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
}
