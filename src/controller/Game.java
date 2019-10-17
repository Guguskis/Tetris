package controller;

import controller.commands.*;
import javafx.scene.paint.Color;
import model.Position;
import model.shapes.Shape;
import model.shapes.ShapeFactory;
import view.renderer.SimpleRenderer;
import model.Grid;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.renderer.Renderer;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Game extends Application {
    private int width;
    private int height;

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
        var gameplayScene = new Scene(root);

        var canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        var graphicsContext = canvas.getGraphicsContext2D();
        var renderer = new SimpleRenderer(graphicsContext, 20);

        var shape = ShapeFactory.makeRandom(0, 0);
        var grid = new Grid(10, 20);

        // Should factory allow to see next shape or should I store current and next shape in here?
        // Was thinking to use more interfaces as a tool for better code reusabilty
        // Look at subscriber pattern
        // Scene builder? I could extract current logic to build gameplay scene
        var commands = getPreparedCommands(shape, grid);
        setupMovementLogic(renderer, gameplayScene, shape, grid, commands);


        primaryStage.setScene(gameplayScene);
        primaryStage.show();
    }

    private void setupMovementLogic(Renderer renderer, Scene gameplayScene, Shape shape, Grid grid, HashMap<KeyCode, CommandInterface> commands) {
        var filteredCommands = commands.entrySet().stream()
                .map(command -> command.getValue())
                .filter(command -> command instanceof MoveDownCommand)
                .collect(Collectors.toList());

        var moveDownCommand = filteredCommands.size() == 1 ? (MoveDownCommand) filteredCommands.get(0) : null;
        if (moveDownCommand != null) {
            automaticallyMoveShapeDown(renderer, moveDownCommand, shape, grid);
        }

        gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            handleCommand(renderer, shape, grid, commands, key);
        });
    }

    private void handleCommand(Renderer renderer, Shape shape, Grid grid, HashMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        var command = getCommand(commands, key);
        if (command != null) {
            command.execute();
            drawScene(renderer, shape, grid);
        }
    }

    private CommandInterface getCommand(HashMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        return commands.get(key.getCode());
    }

    private void drawScene(Renderer renderer, Shape shape, Grid grid) {
        renderer.fillBackground(width, height, Color.rgb(30, 0, 40));
        renderer.outline(new Position(0, 0), grid.getWidth() + 2, grid.getHeight() + 2, Color.rgb(125, 190, 80));
        renderer.mainView(new Position(1, 1), shape, grid);
    }

    private void automaticallyMoveShapeDown(Renderer renderer, MoveDownCommand command, Shape shape, Grid grid) {
        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    drawScene(renderer, shape, grid);
                }
                if (now - lastTick > 250 * 1e6) {
                    lastTick = now;
                    command.execute();
                    drawScene(renderer, shape, grid);
                }
            }
        }.start();
    }

    private HashMap<KeyCode, CommandInterface> getPreparedCommands(Shape shape, Grid grid) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(shape, grid));
        newCommands.put(KeyCode.D, new MoveRightCommand(shape, grid));
        newCommands.put(KeyCode.S, new MoveDownCommand(shape, grid));
        newCommands.put(KeyCode.W, new RotateShapeCommand(shape, grid));

        return newCommands;
    }

}