package model.tetromino;

import model.Tile;

public class LeftLTetromino extends Tetromino {
    public LeftLTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Empty, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
