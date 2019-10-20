package view.scenes;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class GameplayScene extends Parent {
    private final Parent root;
//    private final Scene scene;
    private final Canvas canvas;

    private final int width;
    private final int height;



    public GameplayScene(int width, int height) {
        this.root = new Group();
//        this.scene = new Scene();
        this.canvas = new Canvas();

        this.width = width;
        this.height = height;

//        this.root.getChil
//        this.canvas = new Canvas(width, height);
//        this.root.getChildren().add(canvas);
    }
}
