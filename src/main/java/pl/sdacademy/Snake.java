package pl.sdacademy;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;
    private Direction direction;

    public Snake(Point head, List<Point> body, Direction direction) {
        this.head = head;
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public void expand() {
        body.add(0, head);
        switch (direction) {
            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
        }
    }

    public void cutTail() {
        body.remove(body.size() - 1);
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public boolean contains(Point point) {
        return head.equals(point) || body.contains(point);
    }
}
