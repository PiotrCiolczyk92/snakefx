package pl.sdacademy;

public class SnakeGameConsolePrinter implements SnakeGamePrinter {
    @Override
    public void print(SnakeGame snakeGame) {
        System.out.println(snakeGame);
    }
}
