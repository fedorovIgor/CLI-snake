package org.fedorovigord.model.snake;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.point.PointType;

import java.util.Deque;

public class Down implements SnakeDirection {
    @Override
    public Point moveHead(Deque<Point> position, int downMaxBorder, int rightMaxBorder) {

        var head = new PointImpl(position.peekFirst());

        if (head.getLine() == downMaxBorder)
            head.setLine(0);
        else
            head.shiftDown();

        head.setType(PointType.SNAKE);
        return head;
    }
}
