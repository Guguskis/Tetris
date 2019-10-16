package model;

public class Grid {
    public int[][] map;

    public Grid(int width, int height) {
        this.map = new int[height][width];
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

    public int getTile(Position coordinates) {
        if (inBounds(coordinates)) {
            return map[coordinates.y][coordinates.x];
        }
        return -1;
    }

    public void setTile(Position coordinates, int tile) {
        if (inBounds(coordinates)) {
            map[coordinates.y][coordinates.x] = tile;
        }
    }

    public boolean lineIsEmpty(int y) {
        for (int x = 0; x < getWidth(); x++) {
            if (getTile(new Position(x, y)) != 1) {
                return false;
            }
        }
        return true;
    }

    public void removeLine(int y) {
        for (int x = 0; x < getWidth(); x++) {
            setTile(new Position(x, y), 0);
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
