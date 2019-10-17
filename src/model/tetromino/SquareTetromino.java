package model.tetromino;

import model.Tile;

public class SquareTetromino extends Tetromino {
    public SquareTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied},
        });
    }
}
