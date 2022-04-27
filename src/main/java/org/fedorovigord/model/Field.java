package org.fedorovigord.model;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.snake.SnakeDirection;

import java.util.List;

public interface Field {

    List<Point> getCurrantField();
    Point generateFood();
    void mergeSnakeField(List<Point> snakePosition);

}
