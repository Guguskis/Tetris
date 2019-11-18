package model.tetromino;

import model.Tile;

public class ZTetromino extends Tetromino {
    public ZTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.EMPTY},
                {Tile.EMPTY, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }
}
