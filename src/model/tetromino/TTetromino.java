package model.tetromino;

import model.Tile;

public class TTetromino extends Tetromino {

    public TTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
