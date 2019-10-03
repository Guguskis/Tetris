package ds;

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
    public boolean inGrid(Position coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }
    public int getGridTile(Position coordinates) {
        if (inGrid(coordinates)) {
            return map[coordinates.y][coordinates.x];
        }
        return -1;

    }
}
