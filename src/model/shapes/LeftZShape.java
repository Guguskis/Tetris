package model.shapes;

import model.Tile;

public class LeftZShape extends Shape {
    public LeftZShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
        });
    }
}
