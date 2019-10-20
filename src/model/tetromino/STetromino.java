package model.tetromino;

import model.Tile;

public class STetromino extends Tetromino {
    public STetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
        });
    }
}
