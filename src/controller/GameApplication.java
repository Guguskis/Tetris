package controller;

import controller.commands.CommandInterface;
import controller.commands.MoveDownCommand;
import controller.commands.MoveLeftCommand;
import controller.commands.MoveRightCommand;
import controller.commands.RotateTetrominoCommand;
import controller.rotator.DefaultRotator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Grid;
import view.renderer.DefaultRenderer;
import view.renderer.Renderer;

import java.util.EnumMap;
import java.util.Random;

public class GameApplication extends Application {

    private Group root;
    private GameLogic logic;
    private Renderer renderer;
    private Scene scene;
    private Grid grid;
    private TetrominoConveyor conveyor;
    private EnumMap<KeyCode, CommandInterface> commands = new EnumMap<>(KeyCode.class);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        injectDependencies();
        mapCommandsToKeyboardInput();
    }

    private void injectDependencies() {
        root = new Group();
        grid = new Grid(10, 20);
        logic = new GameLogic(grid, new ScoreKeeper(), new CollisionDetector(), new DefaultRotator());
        TetrominoGenerator generator = new TetrominoGenerator(new Random());
        conveyor = new TetrominoConveyor(grid, generator);

        renderer = getRenderer(grid, logic, conveyor);
        scene = new Scene(root);
    }

    private void mapCommandsToKeyboardInput() {
        prepareCommands();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> handleCommand(commands, key));
    }

    @Override
    public void start(Stage primaryStage) {
        startAutomaticTetrominoMovement();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Renderer getRenderer(Grid grid, GameLogic logic, TetrominoConveyor conveyor) {
        return new DefaultRenderer(root, grid, logic, conveyor);
    }

    private void prepareCommands() {
        commands.put(KeyCode.A, new MoveLeftCommand(conveyor, logic));
        commands.put(KeyCode.LEFT, new MoveLeftCommand(conveyor, logic));

        commands.put(KeyCode.D, new MoveRightCommand(conveyor, logic));
        commands.put(KeyCode.RIGHT, new MoveRightCommand(conveyor, logic));

        commands.put(KeyCode.S, new MoveDownCommand(conveyor, logic));
        commands.put(KeyCode.DOWN, new MoveDownCommand(conveyor, logic));

        commands.put(KeyCode.W, new RotateTetrominoCommand(conveyor, logic));
        commands.put(KeyCode.UP, new RotateTetrominoCommand(conveyor, logic));
    }

    private void handleCommand(EnumMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            renderer.drawFrame();
        }
    }

    private void startAutomaticTetrominoMovement() {
        new AnimationTimer() {
            long lastTick = 0;
            CommandInterface moveDown = commands.get(KeyCode.DOWN);

            @Override
            public void handle(long now) {
                if (!logic.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        renderer.drawFrame();
                    }
                    var gameSpeed = logic.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        moveDown.execute();
                        renderer.drawFrame();
                    }
                }
            }
        }.start();
    }

}