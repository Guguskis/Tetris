package main.model;

public class Position {
    public int x;
    public int y;

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
        return plus(o.x, o.y);
    }

    public Position multiply(int n) {
        var newX = x * n;
        var newY = y * n;
        return new Position(newX, newY);
    }

    public Position plus(int x, int y) {
        var newX = this.x + x;
        var newY = this.y + y;
        return new Position(newX, newY);
    }
}
