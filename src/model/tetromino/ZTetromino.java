package model.tetromino;

import model.Tile;

public class ZTetromino extends Tetromino {
    public ZTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
        });
    }
}
