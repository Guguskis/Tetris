package model.tetromino;

import model.Tile;

public class ITetromino extends Tetromino {
    public ITetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied},
                {Tile.Occupied},
                {Tile.Occupied},
                {Tile.Occupied},
        });
    }
}
