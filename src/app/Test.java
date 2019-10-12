package app;

import ds.Action;
import ds.Grid;
import ds.Position;
import ds.Shape;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {
    static Position position;
    static Shape _shape;
    static Grid _grid;

    public static void main(String[] args) throws InterruptedException {
        _grid = new Grid(5, 15);
        _shape = new Shape(0, 0);

        launch();


    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = new VBox();
        var canvas = new Canvas(400, 400);
        var graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    drawScene(graphicsContext, _grid, _shape);
                    return;
                }
                if (now - lastTick > 1e7) {
                    lastTick = now;
                    drawScene(graphicsContext, _grid, _shape);
                }
            }
        }.start();

        var scene = new Scene(root, 400, 400);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (EventHandler<KeyEvent>) key -> {
            if (key.getCode() == KeyCode.A) {
                Action.moveLeft(_grid, _shape);
            }
            if (key.getCode() == KeyCode.D) {
                Action.moveRight(_grid, _shape);
            }
            if (key.getCode() == KeyCode.S) {
                Action.moveDown(_grid, _shape);
            }
            if (key.getCode() == KeyCode.W) {
                Action.rotateShape(_grid, _shape);
            }
//            if (key.getCode() == KeyCode.X) {
//                Action.debugging(_grid, _shape);
//            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawScene(GraphicsContext gc, Grid grid, Shape shape) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);

        for (int i = -1; i < grid.getHeight() + 1; i++) {
            for (int j = -1; j < grid.getWidth() + 1; j++) {
                var tile = grid.getTile(new Position(j, i));
                if (tile == 1 || tile == -1) {
                    if (tile == 1) {
                        gc.setFill(Color.GREEN);

                    } else if (tile == -1) {
                        gc.setFill(Color.RED);
                    }
                    gc.fillRect((j + 1) * 10, (i + 1) * 10, 10, 10);
                }
            }
        }

        gc.setFill(Color.YELLOW);
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                // Shape getTile will take in unmapped indexes, not mapped.
                // I will need to unmap them before getTile call

                if (shape.getUnmappedTile(new Position(j, i)) == 1) {
                    gc.fillRect((j + 1 + shape.position.x) * 10, (i + 1 + shape.position.y) * 10, 10, 10);
                }
            }
        }
    }
}

