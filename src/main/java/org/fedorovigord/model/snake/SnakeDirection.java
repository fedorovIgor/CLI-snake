package org.fedorovigord.model.snake;

import org.fedorovigord.model.Point;

import java.util.Deque;

@FunctionalInterface
public interface SnakeDirection {
    Point moveHead(Deque<Point> position, int downMaxBorder, int rightMaxBorder);
}



