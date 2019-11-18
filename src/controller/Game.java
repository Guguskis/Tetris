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

public class Game extends Application {
    private int width;
    private int height;

    private Scene gameplayScene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        width = 800;
        height = 800;
        this.gameplayScene = setGameplayScene();

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(gameplayScene);
        primaryStage.show();
        // I finished by implementing some UI elements and encapsulating Tetromino creation
    }

    private Scene setGameplayScene() {
        var root = new Group();
        var scene = new Scene(root);
        var canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        var rules = Rules.getInstance();
        var grid = new Grid(10, 20);
        /* Rules pervadinti i kazka bendresnio (GameLogic)
         * Factory turi tureti tik make, todel reiketu likusia logika iskelti i kita klase
         *
         */
        var tetrominoFactory = new TetrominoFactory(grid);

        var graphicsContext = canvas.getGraphicsContext2D();
        var renderer = new SimpleRenderer(graphicsContext, 20);

        var commands = getPreparedCommands(tetrominoFactory, grid, rules);
        setMovementLogic(renderer, scene, tetrominoFactory, grid, commands, rules);

        return scene;
    }

    private void setMovementLogic(Renderer renderer, Scene gameplayScene, TetrominoFactory factory, Grid grid, HashMap<KeyCode, CommandInterface> commands, Rules rules) {

        automaticallyMoveTetrominoDown(renderer, factory, grid, rules);

        gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            handleCommand(renderer, factory, grid, commands, key, rules);
        });
    }

    private void handleCommand(Renderer renderer, TetrominoFactory factory, Grid grid, HashMap<KeyCode, CommandInterface> commands, KeyEvent key, Rules rules) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            drawScene(renderer, factory, grid, rules);
        }
    }

    private void drawScene(Renderer renderer, TetrominoFactory factory, Grid grid, Rules rules) {
        var currentTetromino = factory.peekCurrent();
        var nextTetromino = factory.peekNext();

        renderer.fillBackground(width, height, Color.rgb(30, 0, 40));
        renderer.outline(new Position(0, 0), grid.getWidth() + 2, grid.getHeight() + 2, Color.rgb(125, 190, 80));
        renderer.mainView(new Position(1, 1), currentTetromino, grid);
        renderer.nextTetromino(new Position(grid.getWidth() + 4, 1), nextTetromino);
        renderer.gameInformation(new Position(grid.getWidth() + 4, 7), rules);
    }

    private void automaticallyMoveTetrominoDown(Renderer renderer, TetrominoFactory factory, Grid grid, Rules rules) {
        new AnimationTimer() {
            long lastTick = 0;
            MoveDownCommand command = new MoveDownCommand(factory, grid, rules);

            @Override
            public void handle(long now) {
                if (!rules.isGameOver()) {
                    if (lastTick == 0) {
                        lastTick = now;
                        drawScene(renderer, factory, grid, rules);
                    }
                    var gameSpeed = rules.getTickIntervalInMilliseconds() * 1e9;
                    if (now - lastTick > gameSpeed) {
                        lastTick = now;
                        command.execute();
                        drawScene(renderer, factory, grid, rules);
                    }
                }
            }
        }.start();
    }

    private HashMap<KeyCode, CommandInterface> getPreparedCommands(TetrominoFactory factory, Grid grid, Rules rules) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(factory, grid, rules));
        newCommands.put(KeyCode.LEFT, new MoveLeftCommand(factory, grid, rules));
        newCommands.put(KeyCode.D, new MoveRightCommand(factory, grid, rules));
        newCommands.put(KeyCode.RIGHT, new MoveRightCommand(factory, grid, rules));
        newCommands.put(KeyCode.S, new MoveDownCommand(factory, grid, rules));
        newCommands.put(KeyCode.DOWN, new MoveDownCommand(factory, grid, rules));
        newCommands.put(KeyCode.W, new RotateTetrominoCommand(factory, grid, rules));
        newCommands.put(KeyCode.UP, new RotateTetrominoCommand(factory, grid, rules));

        return newCommands;
    }

}