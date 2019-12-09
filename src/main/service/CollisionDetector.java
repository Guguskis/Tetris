package main.service;

import main.model.Grid;
import main.model.Vector2;
import main.model.Tile;
import main.model.tetromino.Tetromino;

public class CollisionDetector {

    public boolean hasCollided(Grid grid, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var mappedCoordinates = tetromino.getPosition().plus(new Vector2(j, i));
                var isOverlapped = isOverlapped(grid, tetromino, mappedCoordinates);
                var isOutOfBounds = isOutOfBounds(grid, tetromino, mappedCoordinates);

                if (isOverlapped || isOutOfBounds) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlapped(Grid grid, Tetromino tetromino, Vector2 coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && gridTile == Tile.OCCUPIED;
    }

    private boolean isOutOfBounds(Grid grid, Tetromino tetromino, Vector2 coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && gridTile == Tile.OUT_OF_BOUNDS;
    }
}
