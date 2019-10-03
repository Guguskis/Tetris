package ds;

public class Rules {

    public static void imprintShape(Grid grid, Shape shape) {
        for (int i = 0; i < shape.form.length; i++) {
            for (int j = 0; j < shape.form[0].length; j++) {
                if (shape.form[i][j] == 1) {
                    grid.map[shape.position.y + i][shape.position.x + j] = 1;
                }
            }
        }
    }
    public static boolean hasCollided(Grid grid, Shape shape) {
        for (int i = 0; i < shape.form.length; i++) {
            for (int j = 0; j < shape.form[0].length; j++) {
                var coordinates = new Position(shape.position.x + j, shape.position.y + i);
                if (isOverlapped(grid, shape, coordinates)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOverlapped(Grid grid, Shape shape, Position coordinates) {
        var shapeTile = shape.getTile(coordinates);
        var gridTile = grid.getGridTile(coordinates);

        if (shapeTile == 1 && gridTile == 1) {
            return true;
        } else if (shapeTile == 1 && gridTile == -1) {
            return true;
        }
        return false;
    }

}
