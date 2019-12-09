package main.service;

import main.model.Playfield;
import main.model.Vector2;
import main.model.Tile;
import main.model.tetromino.Tetromino;

public class CollisionDetector {

    public boolean hasCollided(Playfield playfield, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var mappedCoordinates = tetromino.getPosition().plus(new Vector2(j, i));
                var isOverlapped = isOverlapped(playfield, tetromino, mappedCoordinates);
                var isOutOfBounds = isOutOfBounds(playfield, tetromino, mappedCoordinates);

                if (isOverlapped || isOutOfBounds) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlapped(Playfield playfield, Tetromino tetromino, Vector2 coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var playfieldTile = playfield.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && playfieldTile == Tile.OCCUPIED;
    }

    private boolean isOutOfBounds(Playfield playfield, Tetromino tetromino, Vector2 coordinates) {
        var tetrominoTile = tetromino.getMappedTile(coordinates);
        var playfieldTile = playfield.getTile(coordinates);

        return tetrominoTile == Tile.OCCUPIED && playfieldTile == Tile.OUT_OF_BOUNDS;
    }
}
