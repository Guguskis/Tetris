package ds;

public class Rules {

    public static void imprintShape(Grid grid, Shape shape) {
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                var coordinates = new Position(j, i);
                var shapeTile = shape.getUnmappedTile(coordinates);

                if (shapeTile == 1) {
                    var mappedCoordinates = coordinates.plus(shape.position);
                    grid.setTile(mappedCoordinates, shapeTile);
                }
            }
        }
    }

    public static boolean hasCollided(Grid grid, Shape shape) {
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                var mappedCoordinates = shape.position.plus(new Position(j, i));
                var isOverlapped = isOverlapped(grid, shape, mappedCoordinates);
                var isOutOfBounds = isOutOfBounds(grid, shape, mappedCoordinates);

                if (isOverlapped || isOutOfBounds) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOverlapped(Grid grid, Shape shape, Position coordinates) {
        var shapeTile = shape.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        if (shapeTile == 1 && gridTile == 1) {
            return true;
        }
        return false;
    }

    private static boolean isOutOfBounds(Grid grid, Shape shape, Position coordinates) {
        var shapeTile = shape.getMappedTile(coordinates);
        var gridTile = grid.getTile(coordinates);

        if (shapeTile == 1 && gridTile == -1) {
            return true;
        }
        return false;
    }


    public static void removeFilledLines(Grid grid) {
        for (int y = grid.getHeight() - 1; y >= 0; y--) {
            if (grid.lineIsEmpty(y)) {
                grid.removeLine(y);

                for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
                    grid.pushLineDown(lineAbove);
                }
                y++;
            }

        }
    }
}
