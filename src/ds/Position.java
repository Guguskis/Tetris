package ds;

public class Position {
    public int x = 0;
    public int y = 0;

    public Position() {

    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void moveLeft() {
        x-=1;
    }

    public void moveRight() {
        x+=1;
    }

    public void moveDown() {
        y+=1;
    }
    public void moveUp(){
        y-=1;
    }

    public Position minus(Position o){
        var newX=x-o.x;
        var newY=y-o.y;
        return new Position(newX, newY);
    }
}
