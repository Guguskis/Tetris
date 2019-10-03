package ds;

public class AsciiGraphics {
    public AsciiGraphics(){

    }

    public void drawMap(Grid grid, Shape shape) {
        for (int i = -1; i <= grid.getHeight(); i++) {
            for (int j = -1; j <= grid.getWidth(); j++) {
                if (i == -1 || j == -1 || i == grid.getHeight() || j == grid.getWidth()) {
                    System.out.print("w");
                } else {
                    var shapeTile = shape.getTile(new Position(j, i));
                    var gridTile = grid.getGridTile(new Position(j, i));

                    if (shapeTile == 1 || gridTile == 1) {
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
