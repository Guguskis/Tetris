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

    private GameLogic logic;
    private Renderer renderer;
    private Scene scene;
    private Grid grid;
    private TetrominoConveyor conveyor;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        injectDependencies();
        mapCommandsToKeyboardInput();
    }

    private void injectDependencies() {
        grid = new Grid(10, 20);
        logic = new GameLogic(new ScoreKeeper());

        TetrominoGenerator generator = new TetrominoGenerator(new Random());
        conveyor = new TetrominoConveyor(grid, generator);

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

        commands.put(KeyCode.A, new MoveLeftCommand(conveyor, grid, logic));
        commands.put(KeyCode.LEFT, new MoveLeftCommand(conveyor, grid, logic));

        commands.put(KeyCode.D, new MoveRightCommand(conveyor, grid, logic));
        commands.put(KeyCode.RIGHT, new MoveRightCommand(conveyor, grid, logic));

        commands.put(KeyCode.S, new MoveDownCommand(conveyor, grid, logic));
        commands.put(KeyCode.DOWN, new MoveDownCommand(conveyor, grid, logic));

        commands.put(KeyCode.W, new RotateTetrominoCommand(conveyor, grid, logic));
        commands.put(KeyCode.UP, new RotateTetrominoCommand(conveyor, grid, logic));

        return commands;
    }

    private void handleCommand(EnumMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            renderer.drawFrame(grid, logic, conveyor);
        }
    }

    private void startAutomaticTetrominoMovement() {
        new AnimationTimer() {
            long lastTick = 0;
            MoveDownCommand command = new MoveDownCommand(conveyor, grid, logic);

            @Override
            public void handle(long now) {
                if (!logic.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        renderer.drawFrame(grid, logic, conveyor);
                    }
                    var gameSpeed = logic.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        command.execute();
                        renderer.drawFrame(grid, logic, conveyor);
                    }
                }
            }
        }.start();
    }

}