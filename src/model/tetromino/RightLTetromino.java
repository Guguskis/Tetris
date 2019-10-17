package model.tetromino;

import model.Tile;

public class RightLTetromino extends Tetromino {
    public RightLTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Empty, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
