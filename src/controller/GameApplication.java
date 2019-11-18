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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Grid;
import model.Position;
import model.tetromino.TetrominoFactory;
import view.renderer.Renderer;
import view.renderer.SimpleRenderer;

import java.util.HashMap;

public class GameApplication extends Application {
    private int width = 800;
    private int height = 800;
    private Renderer renderer;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(getMainScene());
        primaryStage.show();
    }

    private Scene getMainScene() {
        var root = new Group();
        var scene = new Scene(root);

        renderer = getRenderer(root, 20);

        var grid = new Grid(10, 20);
        var tetrominoFactory = new TetrominoFactory(grid);

        setMovementLogic(scene, tetrominoFactory, grid, new GameLogic(new ScoreCalculator()));

        return scene;
    }

    private Renderer getRenderer(Group root, int scale) {
        var canvas = new Canvas(width, height);
        var graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        return new SimpleRenderer(graphicsContext, scale);
    }

    private void setMovementLogic(Scene gameplayScene, TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        HashMap<KeyCode, CommandInterface> commands = getPreparedCommands(factory, grid, gameLogic);
        automaticallyMoveTetrominoDown(factory, grid, gameLogic);

        gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            handleCommand(factory, grid, commands, key, gameLogic);
        });
    }

    private void handleCommand(TetrominoFactory factory, Grid grid, HashMap<KeyCode, CommandInterface> commands, KeyEvent key, GameLogic gameLogic) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            drawScene(factory, grid, gameLogic);
        }
    }

    private void drawScene(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        var currentTetromino = factory.peekCurrent();
        var nextTetromino = factory.peekNext();

        renderer.fillBackground(width, height, Color.rgb(30, 0, 40));
        renderer.outline(new Position(0, 0), grid.getWidth() + 2, grid.getHeight() + 2, Color.rgb(125, 190, 80));
        renderer.mainView(new Position(1, 1), currentTetromino, grid);
        renderer.nextTetromino(new Position(grid.getWidth() + 4, 1), nextTetromino);
        renderer.gameInformation(new Position(grid.getWidth() + 4, 7), gameLogic);
    }

    private void automaticallyMoveTetrominoDown(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        new AnimationTimer() {
            long lastTick = 0;
            MoveDownCommand command = new MoveDownCommand(factory, grid, gameLogic);

            @Override
            public void handle(long now) {
                if (!gameLogic.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        drawScene(factory, grid, gameLogic);
                    }
                    var gameSpeed = gameLogic.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        command.execute();
                        drawScene(factory, grid, gameLogic);
                    }
                }
            }
        }.start();
    }

    private HashMap<KeyCode, CommandInterface> getPreparedCommands(TetrominoFactory factory, Grid grid, GameLogic gameLogic) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.LEFT, new MoveLeftCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.D, new MoveRightCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.RIGHT, new MoveRightCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.S, new MoveDownCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.DOWN, new MoveDownCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.W, new RotateTetrominoCommand(factory, grid, gameLogic));
        newCommands.put(KeyCode.UP, new RotateTetrominoCommand(factory, grid, gameLogic));

        return newCommands;
    }

}