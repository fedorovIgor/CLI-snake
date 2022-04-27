package org.fedorovigord.model.snake;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.Snake;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.point.PointType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SnakeImpl implements Snake {
    private final Deque<Point> points = new ArrayDeque<>();
    private int linesTotal;
    private int columnsTotal;
    private SnakeDirection direction;
    private Point foodPoint;

    public SnakeImpl(int linesTotal, int columnsTotal) {
        this.linesTotal = linesTotal;
        this.columnsTotal = columnsTotal;
        direction = new Left();

        initPosition(linesTotal, columnsTotal);
    }

    @Override
    public List<Point> getCurrantPoints() {
        return points.stream().collect(Collectors.toList());
    }

    @Override
    public void setDirection(SnakeDirection direction) {
        if ((Up.class == direction.getClass() && this.direction.getClass() == Down.class)
            || (Down.class == direction.getClass() && this.direction.getClass() == Up.class)
            || (Left.class == direction.getClass() && this.direction.getClass() == Right.class)
            || (Right.class == direction.getClass() && this.direction.getClass() == Left.class))
            return;

        this.direction = direction;
    }

    @Override
    public void move() {
        var nextHead = direction.
                moveHead(points, linesTotal -1, columnsTotal - 1);


        if (! (nextHead.getLine() == (foodPoint.getLine())
            && (nextHead.getColumn() == (foodPoint.getColumn()))))
            points.pollLast();

        points.offerFirst(nextHead);

    }

    @Override
    public boolean isCrashed() {

        var map = new HashMap<Point, Integer>();

        for (var i : points) {
            if (map.containsKey(i))
                return true;
            else
                map.put(i, 0);
        }

        return false;
    }

    @Override
    public void setFoodPoint(Point point) {
        this.foodPoint = point;
    }
    public void initPosition(int lines, int columns) {
        int middleLine = lines >> 1;
        int middleColumn = columns >> 1;

        points.offerFirst( new PointImpl(middleLine, middleColumn, PointType.SNAKE));
        points.offerLast( new PointImpl(middleLine, middleColumn + 1, PointType.SNAKE));
    }

    @Override
    public String toString() {
        return "Snake{" +
                "points=" + points +
                ", linesTotal=" + linesTotal +
                ", columnsTotal=" + columnsTotal +
                ", direction=" + direction +
                '}';
    }
}
