package app;

import ds.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 15);

        Scanner scan = new Scanner(System.in);
        var graphics = new AsciiGraphics();
        var shape = new Shape(0, 0);

        while (true) {
            graphics.drawMap(grid, shape);

            var command = scan.nextLine();
            switch (command) {
                case "a":
                    Action.moveLeft(grid, shape);
                    break;
                case "d":
                    Action.moveRight(grid, shape);
                    break;
                case "w":
                    Action.rotateShape(grid, shape);
                    break;
                case "s":
                    Action.moveDown(grid, shape);
                    break;
            }
        }
    }

}
