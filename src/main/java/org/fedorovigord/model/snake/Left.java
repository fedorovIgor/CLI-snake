package org.fedorovigord.model.snake;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.point.PointType;

import java.util.Deque;

public class Left implements SnakeDirection {
    @Override
    public Point moveHead(Deque<Point> position, int downMaxBorder, int rightMaxBorder) {
        var head = new PointImpl(position.peekFirst());

        if (head.getColumn() == 0)
            head.setColumn(rightMaxBorder);
        else
            head.shiftLeft();

        head.setType(PointType.SNAKE);
        return head;
    }
}
