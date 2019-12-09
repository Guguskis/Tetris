package test.service;

import main.model.Playfield;
import main.model.Tile;
import main.model.tetromino.Tetromino;
import main.model.tetromino.TetrominoFactory;
import main.service.CollisionDetector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionDetectorTest {
    private CollisionDetector detector = new CollisionDetector();

    @Test
    public void tilesDontOverlap_ReturnsFalse() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.EMPTY, Tile.OCCUPIED, Tile.EMPTY},
        });
        Tetromino tetromino = TetrominoFactory.make(0, 0, new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.OCCUPIED},
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertFalse(actual);
    }

    @Test
    public void tilesOverlap_ReturnsTrue() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED}
        });
        Tetromino tetromino = TetrominoFactory.make(0, 0, new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.OCCUPIED}
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertTrue(actual);
    }


    @Test
    public void tetrominoTileOutsidePlayfield_ReturnsTrue() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY},
        });
        Tetromino tetromino = TetrominoFactory.make(0, 0, new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.OCCUPIED}
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertTrue(actual);
    }

    @Test
    public void emptyTetrominoTileOutsidePlayfield_ReturnsFalse() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY},
        });
        Tetromino tetromino = TetrominoFactory.make(0, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.EMPTY}
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertFalse(actual);
    }


    @Test
    public void tetrominoPositionChangedAndTilesDontOverlap_ReturnsFalse() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.EMPTY},
                {Tile.EMPTY, Tile.EMPTY, Tile.EMPTY}
        });
        Tetromino tetromino = TetrominoFactory.make(1, 1, new Tile[][]{
                {Tile.OCCUPIED},
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertFalse(actual);
    }

    @Test
    public void tetrominoPositionChangedAndTilesOverlap_ReturnsTrue() {
        Playfield playfield = new Playfield(new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.EMPTY},
                {Tile.EMPTY, Tile.OCCUPIED, Tile.EMPTY}
        });
        Tetromino tetromino = TetrominoFactory.make(1, 1, new Tile[][]{
                {Tile.OCCUPIED},
        });

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertTrue(actual);
    }


}