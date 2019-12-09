package main.model;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 minus(Vector2 o) {
        var newX = x - o.x;
        var newY = y - o.y;
        return new Vector2(newX, newY);
    }

    public Vector2 plus(Vector2 o) {
        return plus(o.x, o.y);
    }

    public Vector2 multiply(int n) {
        var newX = x * n;
        var newY = y * n;
        return new Vector2(newX, newY);
    }

    public Vector2 plus(int x, int y) {
        var newX = this.x + x;
        var newY = this.y + y;
        return new Vector2(newX, newY);
    }
}
