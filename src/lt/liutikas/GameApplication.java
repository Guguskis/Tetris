package lt.liutikas;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lt.liutikas.controller.CollisionDetector;
import lt.liutikas.controller.GameLogic;
import lt.liutikas.controller.RandomTetrominoGenerator;
import lt.liutikas.controller.ScoreKeeper;
import lt.liutikas.controller.TetrominoConveyor;
import lt.liutikas.controller.commands.Command;
import lt.liutikas.controller.commands.MoveDownCommand;
import lt.liutikas.controller.commands.MoveLeftCommand;
import lt.liutikas.controller.commands.MoveRightCommand;
import lt.liutikas.controller.commands.RotateTetrominoCommand;
import lt.liutikas.controller.rotator.DefaultRotator;
import lt.liutikas.model.Grid;
import lt.liutikas.renderer.DefaultRenderer;
import lt.liutikas.renderer.Renderer;

import java.util.EnumMap;
import java.util.Random;

public class GameApplication extends Application {

    private GameLogic logic;
    private Renderer renderer;
    private Scene scene;
    private TetrominoConveyor conveyor;
    private EnumMap<KeyCode, Command> commands = new EnumMap<>(KeyCode.class);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        applyConfiguration();
        mapCommandsToKeyboardInput();
    }

    private void applyConfiguration() {
        Grid grid = new Grid(10, 20);

        logic = new GameLogic(grid, new ScoreKeeper(), new CollisionDetector(), new DefaultRotator());
        conveyor = new TetrominoConveyor(grid, new RandomTetrominoGenerator(new Random()));

        Group root = new Group();
        scene = new Scene(root);
        renderer = new DefaultRenderer(root, grid, logic, conveyor);
    }

    private void mapCommandsToKeyboardInput() {
        prepareCommands();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleCommand);
    }

    @Override
    public void start(Stage primaryStage) {
        setAutomaticMoveDown();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Todo move to UserInput class
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

    private void handleCommand(KeyEvent key) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            renderer.drawFrame();
        }
    }

    private void setAutomaticMoveDown() {
        new AnimationTimer() {
            long lastTick = 0;
            Command moveDown = new MoveDownCommand(conveyor, logic);

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