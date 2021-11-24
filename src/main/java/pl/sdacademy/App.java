package pl.sdacademy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;
    private static final int POINT_SIZE = 20;

    private GraphicsContext graphicsContext;
    private SnakeGame snakeGame;
//
    @Override
    public void start(Stage stage) {
        initializeUuserInterface(stage);
        initializeSnakeGame();
    }

    private void initializeSnakeGame() {
        SnakeGameJavaFxPrinter snakeGamePrinter = new SnakeGameJavaFxPrinter(graphicsContext, POINT_SIZE);
        int xBound = CANVAS_WIDTH / POINT_SIZE;
        int yBound = CANVAS_HEIGHT / POINT_SIZE;
        snakeGame = new SnakeGame(xBound, yBound, snakeGamePrinter);
        Thread thread = new Thread(snakeGame::start); // new Thread(() -> snakeGame.start());
        thread.setDaemon(true);
        thread.start();
    }

    private void initializeUuserInterface(Stage stage) {
        HBox hBox = new HBox();
        Button BtnUp = new Button("góra");
        BtnUp.setFocusTraversable(false);
        BtnUp.setOnAction(event -> snakeGame.setSnakeDirection(Direction.UP));
        Button BtnLeft = new Button("lewo");
        BtnLeft.setFocusTraversable(false);
        BtnLeft.setOnAction(event -> snakeGame.setSnakeDirection(Direction.LEFT));
        Button BtnRight = new Button("prawo");
        BtnRight.setFocusTraversable(false);
        BtnRight.setOnAction(event -> snakeGame.setSnakeDirection(Direction.RIGHT));
        Button BtnDown = new Button("dół");
        BtnDown.setFocusTraversable(false);
        BtnDown.setOnAction(event -> snakeGame.setSnakeDirection(Direction.DOWN));
        hBox.getChildren()
                .addAll(
                        BtnUp,
                        BtnLeft,
                        BtnRight,
                        BtnDown
                );
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();
        VBox vBox = new VBox(canvas, hBox);
        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(keyEvent -> {
            switch(keyEvent.getCode()) {
                case LEFT:
                    snakeGame.setSnakeDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    snakeGame.setSnakeDirection(Direction.RIGHT);
                    break;
                case UP:
                    snakeGame.setSnakeDirection(Direction.UP);
                    break;
                case DOWN:
                    snakeGame.setSnakeDirection(Direction.DOWN);
                    break;
            }
        });
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}