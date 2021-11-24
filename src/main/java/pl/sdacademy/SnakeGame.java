package pl.sdacademy;

import java.util.Collections;
import java.util.Random;

public class SnakeGame {
    private int xBound;
    private int yBound;
    private Snake snake;
    private Point apple;
    private SnakeGamePrinter printer;

    public SnakeGame(int xBound, int yBound, Snake snake, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = snake;
        this.printer = printer;
    }

    public SnakeGame(int xBound, int yBound, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.printer = printer;
        snake = new Snake(new Point(0, 0), Collections.emptyList(), Direction.RIGHT);
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public int getxBound() {
        return xBound;
    }

    public int getYBound() {
        return yBound;
    }

    public void start() {
        randomizeApple();
        printer.print(this);
        while (isSnakeInBounds()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            snake.expand();
            if (doesSnakeEatApple()) {
                randomizeApple();
            } else {
                snake.cutTail();
            }
            printer.print(this);
        }
    }

    private boolean doesSnakeEatApple() {
        return snake.getHead().equals(apple);
    }

    private boolean isSnakeInBounds() {
        Point head = snake.getHead();
        int headX = head.getX();
        int headY = head.getY();
        return headY >= 0 && headY < yBound
                && headX >= 0 && headX < xBound;
    }

    private void randomizeApple() {
        Random random = new Random();
        do {
            int x = random.nextInt(xBound);
            int y = random.nextInt(yBound);
            apple = new Point(x, y);
        } while (snake.contains(apple));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < yBound; y++) {
            for (int x = 0; x < xBound; x++) {
                Point point = new Point(x, y);
                char boardCharacter = getBoardCharacterAt(point);
                stringBuilder.append(boardCharacter);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    private char getBoardCharacterAt(Point point) {
        if (snake.getHead().equals(point)) {
            return 'H';
        } else if (snake.getBody().contains(point)) {
            return 'B';
        } else if (apple.equals(point)) {
            return 'A';
        } else {
            return '.';
        }
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }


}
