package controller;

import controller.commands.CommandInterface;
import controller.commands.MoveDownCommand;
import controller.commands.MoveLeftCommand;
import controller.commands.MoveRightCommand;
import controller.commands.RotateTetrominoCommand;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Grid;
import model.tetromino.TetrominoConveyor;
import view.renderer.Renderer;
import view.renderer.SimpleRenderer;

import java.util.EnumMap;
import java.util.Random;

public class GameApplication extends Application {
    private int width = 800;
    private int height = 800;
    private int pixelScale = 20;

    private GameLogic gameLogic;
    private Renderer renderer;
    private Scene scene;
    private Grid grid;
    private TetrominoConveyor tetrominoManager;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        initialiseFields();
        mapCommandsToKeyboardInput();
    }

    private void initialiseFields() {
        grid = new Grid(10, 20);
        tetrominoManager = new TetrominoConveyor(grid, new Random());
        gameLogic = new GameLogic();

        Group root = new Group();
        renderer = getRenderer(root);
        scene = new Scene(root);
    }

    private void mapCommandsToKeyboardInput() {
        EnumMap<KeyCode, CommandInterface> commands = getPreparedCommands();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> handleCommand(commands, key));
    }

    @Override
    public void start(Stage primaryStage) {
        startAutomaticTetrominoMovement();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Renderer getRenderer(Group root) {
        var canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        return new SimpleRenderer(canvas.getGraphicsContext2D(), width, height, pixelScale);
    }

    private EnumMap<KeyCode, CommandInterface> getPreparedCommands() {
        EnumMap<KeyCode, CommandInterface> commands = new EnumMap<>(KeyCode.class);

        commands.put(KeyCode.A, new MoveLeftCommand(tetrominoManager, grid, gameLogic));
        commands.put(KeyCode.LEFT, new MoveLeftCommand(tetrominoManager, grid, gameLogic));

        commands.put(KeyCode.D, new MoveRightCommand(tetrominoManager, grid, gameLogic));
        commands.put(KeyCode.RIGHT, new MoveRightCommand(tetrominoManager, grid, gameLogic));

        commands.put(KeyCode.S, new MoveDownCommand(tetrominoManager, grid, gameLogic));
        commands.put(KeyCode.DOWN, new MoveDownCommand(tetrominoManager, grid, gameLogic));

        commands.put(KeyCode.W, new RotateTetrominoCommand(tetrominoManager, grid, gameLogic));
        commands.put(KeyCode.UP, new RotateTetrominoCommand(tetrominoManager, grid, gameLogic));

        return commands;
    }

    private void handleCommand(EnumMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            renderer.drawFrame(grid, gameLogic, tetrominoManager);
        }
    }

    private void startAutomaticTetrominoMovement() {
        new AnimationTimer() {
            long lastTick = 0;
            MoveDownCommand command = new MoveDownCommand(tetrominoManager, grid, gameLogic);

            @Override
            public void handle(long now) {
                if (!gameLogic.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        renderer.drawFrame(grid, gameLogic, tetrominoManager);
                    }
                    var gameSpeed = gameLogic.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        command.execute();
                        renderer.drawFrame(grid, gameLogic, tetrominoManager);
                    }
                }
            }
        }.start();
    }

}