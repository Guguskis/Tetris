package model.tetromino;

import model.Tile;

public class LongTetromino extends Tetromino {
    public LongTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
