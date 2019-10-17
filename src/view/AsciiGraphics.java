package view;

import model.Grid;
import model.Position;
import model.Shape;
import model.Tile;

public class AsciiGraphics {
    public AsciiGraphics(){

    }

    public void drawMap(Grid grid, Shape shape) {
        for (int i = -1; i <= grid.getHeight(); i++) {
            for (int j = -1; j <= grid.getWidth(); j++) {
                if (i == -1 || j == -1 || i == grid.getHeight() || j == grid.getWidth()) {
                    System.out.print("w");
                } else {
                    var shapeTile = shape.getUnmappedTile(new Position(j, i));
                    var gridTile = grid.getTile(new Position(j, i));

                    if (shapeTile == Tile.Occupied|| gridTile == Tile.Occupied) {
                        System.out.print("x");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

}
