package main.model.tetromino;

import main.model.Tile;

import java.util.NoSuchElementException;

public class TetrominoFactory {
    private TetrominoFactory() {

    }

    public static Tetromino getInstance(TetrominoType type, int x) {
        switch (type) {
            case I:
                return getITetromino(x);
            case J:
                return getJTetromino(x);
            case L:
                return getLTetromino(x);
            case O:
                return getOTetromino(x);
            case S:
                return getSTetromino(x);
            case T:
                return getTTetromino(x);
            case Z:
                return getZTetromino(x);
            default:
                throw new NoSuchElementException(getNoSuchElementMessage(type.toString()));
        }
    }

    public static Tetromino make(int x, int y, Tile[][] tiles) {
        return new Tetromino(x, y, tiles);
    }

    private static Tetromino getZTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.EMPTY},
                {Tile.EMPTY, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }

    private static Tetromino getTTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.OCCUPIED, Tile.EMPTY},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }

    private static Tetromino getSTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.OCCUPIED, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.EMPTY},
        });
    }

    private static Tetromino getOTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }

    private static Tetromino getLTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }

    private static Tetromino getJTetromino(int x) {
        return new Tetromino(x - 1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.EMPTY, Tile.EMPTY},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }

    private static Tetromino getITetromino(int x) {
        return new Tetromino(x - 2, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED}
        });
    }

    private static String getNoSuchElementMessage(String type) {
        return "\"" + type + "\"" + " does not exist.";
    }
}
