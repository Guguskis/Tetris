package model.shapes;

import model.Tile;

public class SquareShape extends Shape {
    public SquareShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied},
        });
    }
}
