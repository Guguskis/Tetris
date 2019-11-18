package model.tetromino;

import model.Tile;

public class STetromino extends Tetromino {
    public STetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.OCCUPIED, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.EMPTY},
        });
    }
}
