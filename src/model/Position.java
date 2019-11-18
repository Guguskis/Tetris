package model;

public class Position {
    public int x;
    public int y;

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position minus(Position o) {
        var newX = x - o.x;
        var newY = y - o.y;
        return new Position(newX, newY);
    }

    public Position plus(Position o) {
        var newX = x + o.x;
        var newY = y + o.y;
        return new Position(newX, newY);
    }

    public Position multiply(int n) {
        var newX = x * n;
        var newY = y * n;
        return new Position(newX, newY);
    }
}
