package org.fedorovigord.model.snake;


import org.fedorovigord.model.Point;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.point.PointType;

import java.util.Deque;

public class Right implements SnakeDirection {
    public Point moveHead(Deque<Point> position, int downMaxBorder, int rightMaxBorder) {
        var head = new PointImpl(position.peekFirst());

        if (head.getColumn() == rightMaxBorder)
            head.setColumn(0);
        else
            head.shiftRight();

        head.setType(PointType.SNAKE);
        return head;
    }
}
