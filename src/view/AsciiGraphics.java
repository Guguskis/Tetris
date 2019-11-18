package view;

import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;

public class AsciiGraphics {
    public AsciiGraphics(){

    }

    public void drawMap(Grid grid, Tetromino tetromino) {
        for (int i = -1; i <= grid.getHeight(); i++) {
            for (int j = -1; j <= grid.getWidth(); j++) {
                if (i == -1 || j == -1 || i == grid.getHeight() || j == grid.getWidth()) {
                    System.out.print("w");
                } else {
                    var tetrominoTile = tetromino.getUnmappedTile(new Position(j, i));
                    var gridTile = grid.getTile(new Position(j, i));

                    if (tetrominoTile == Tile.OCCUPIED || gridTile == Tile.OCCUPIED) {
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
