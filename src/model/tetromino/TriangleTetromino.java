package model.tetromino;

import model.Tile;

public class TriangleTetromino extends Tetromino {

    public TriangleTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
