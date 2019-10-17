package model.shapes;

import model.Tile;

public class RightZShape extends Shape {
    public RightZShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
        });
    }
}
