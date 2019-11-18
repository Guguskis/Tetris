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
    private Scene scene;
    private Grid grid;
    private TetrominoFactory tetrominoFactory;
    private GameLogic gameLogic;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        Group root = new Group();

        scene = new Scene(root);
        renderer = getRenderer(root, 20);
        grid = new Grid(10, 20);
        tetrominoFactory = new TetrominoFactory(grid);
        gameLogic = new GameLogic();

        setMovementLogic();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Renderer getRenderer(Group root, int scale) {
        var canvas = new Canvas(width, height);
        var graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        return new SimpleRenderer(graphicsContext, scale);
    }

    private void setMovementLogic() {
        HashMap<KeyCode, CommandInterface> commands = getPreparedCommands(grid, gameLogic);
        automaticallyMoveTetrominoDown(grid, gameLogic);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            handleCommand(grid, commands, key, gameLogic);
        });
    }

    private void handleCommand(Grid grid, HashMap<KeyCode, CommandInterface> commands, KeyEvent key, GameLogic gameLogic) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            drawScene(grid, gameLogic);
        }
    }

    private void drawScene(Grid grid, GameLogic gameLogic) {
        var currentTetromino = tetrominoFactory.peekCurrent();
        var nextTetromino = tetrominoFactory.peekNext();

        renderer.fillBackground(width, height, Color.rgb(30, 0, 40));
        renderer.outline(new Position(0, 0), grid.getWidth() + 2, grid.getHeight() + 2, Color.rgb(125, 190, 80));
        renderer.mainView(new Position(1, 1), currentTetromino, grid);
        renderer.nextTetromino(new Position(grid.getWidth() + 4, 1), nextTetromino);
        renderer.gameInformation(new Position(grid.getWidth() + 4, 7), gameLogic);
    }

    private void automaticallyMoveTetrominoDown(Grid grid, GameLogic gameLogic) {
        new AnimationTimer() {
            long lastTick = 0;
            MoveDownCommand command = new MoveDownCommand(tetrominoFactory, grid, gameLogic);

            @Override
            public void handle(long now) {
                if (!gameLogic.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        drawScene(grid, gameLogic);
                    }
                    var gameSpeed = gameLogic.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        command.execute();
                        drawScene(grid, gameLogic);
                    }
                }
            }
        }.start();
    }

    private HashMap<KeyCode, CommandInterface> getPreparedCommands(Grid grid, GameLogic gameLogic) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.LEFT, new MoveLeftCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.D, new MoveRightCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.RIGHT, new MoveRightCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.S, new MoveDownCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.DOWN, new MoveDownCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.W, new RotateTetrominoCommand(tetrominoFactory, grid, gameLogic));
        newCommands.put(KeyCode.UP, new RotateTetrominoCommand(tetrominoFactory, grid, gameLogic));

        return newCommands;
    }

}