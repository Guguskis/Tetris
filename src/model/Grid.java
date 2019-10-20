package model;

public class Grid {
    public Tile[][] map;

    public Grid(int width, int height) {
        this.map = new Tile[height][width];
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public boolean inBounds(Position coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    public Tile getTile(Position coordinates) {
        if (inBounds(coordinates)) {
            return map[coordinates.y][coordinates.x];
        } else if (coordinates.y < 0) {
            return Tile.Empty;
        }
        return Tile.OutOfBounds;
    }

    public void setTile(Position coordinates, Tile tile) {
        if (inBounds(coordinates)) {
            map[coordinates.y][coordinates.x] = tile;
        }
    }

    public boolean lineIsEmpty(int y) {
        for (int x = 0; x < getWidth(); x++) {
            if (getTile(new Position(x, y)) != Tile.Occupied) {
                return false;
            }
        }
        return true;
    }

    public void removeLine(int y) {
        for (int x = 0; x < getWidth(); x++) {
            setTile(new Position(x, y), Tile.Empty);
        }
    }

    public void pushLineDown(int y) {
        for (int x = 0; x < getWidth(); x++) {
            var tile = getTile(new Position(x, y));
            setTile(new Position(x, y + 1), tile);
        }
        removeLine(y);
    }
}
