package app;

import commands.*;
import ds.GraphicsManager;
import ds.Grid;
import ds.Shape;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;

public class Game extends Application {
    private int width;
    private int height;
    private int scale = 20;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        width = 800;
        height = 800;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        var root = new Group();
        var canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        var graphicsContext = canvas.getGraphicsContext2D();
        var graphicsManager = new GraphicsManager(graphicsContext, 20);
        var gameplayScene = new Scene(root);

        var shape = new Shape(0, 0);
        var grid = new Grid(10, 20);

        graphicsManager.drawScene(shape, grid);
        // Finished by making the code work again. Was thinking about writing end condition
        var commands = getPrepareCommands(shape, grid);

        automaticallyMoveShapeDown(graphicsManager, commands.get(KeyCode.S), shape, grid);
        gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, (EventHandler<KeyEvent>) key -> {
            var command = commands.get(key.getCode());
            if (command != null) {
                command.execute();
                graphicsManager.drawScene(shape, grid);
            }
        });

        primaryStage.setScene(gameplayScene);
        primaryStage.show();
    }


    private void automaticallyMoveShapeDown(GraphicsManager graphicsManager, CommandInterface moveDownCommand, Shape shape, Grid grid) {
        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    graphicsManager.drawScene(shape, grid);

                }
                if (now - lastTick > 250 * 1e6) {
                    lastTick = now;
                    moveDownCommand.execute();
                    graphicsManager.drawScene(shape, grid);


                }
            }
        }.start();
    }


    private HashMap<KeyCode, CommandInterface> getPrepareCommands(Shape shape, Grid grid) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(shape, grid));
        newCommands.put(KeyCode.D, new MoveRightCommand(shape, grid));
        newCommands.put(KeyCode.S, new MoveDownCommand(shape, grid));
        newCommands.put(KeyCode.W, new RotateShapeCommand(shape, grid));

        return newCommands;
    }

}