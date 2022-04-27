package org.fedorovigord.model;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.snake.SnakeDirection;

import java.util.List;

public interface Snake {
    List<Point> getCurrantPoints();

    void setDirection(SnakeDirection direction);

    void move();

    boolean isCrashed();

    void setFoodPoint(Point point);
}
