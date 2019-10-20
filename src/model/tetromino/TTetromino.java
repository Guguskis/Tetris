package model.tetromino;

import model.Tile;

public class TTetromino extends Tetromino {

    public TTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
