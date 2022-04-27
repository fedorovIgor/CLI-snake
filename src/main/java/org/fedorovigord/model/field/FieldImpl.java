package org.fedorovigord.model.field;

import org.fedorovigord.model.Field;
import org.fedorovigord.model.Point;
import org.fedorovigord.model.Snake;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.point.PointType;
import org.fedorovigord.model.snake.SnakeDirection;
import org.fedorovigord.model.snake.SnakeImpl;
import org.fedorovigord.view.SnakeConsoleView;
import org.fedorovigord.view.SnakeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FieldImpl implements Field {

    private final List<Point> field = new ArrayList<>();
    private int lineSize;
    private int columnSize;

    public FieldImpl(int lineSize, int columnSize) {

        if (lineSize < 3 || columnSize < 3)
            throw new RuntimeException("should be large than 3");

        this.lineSize = lineSize;
        this.columnSize = columnSize;

        generateField();
    }


    private void generateField() {

        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                Point p = new PointImpl(i,j);
                field.add(p);
            }
        }

    }


    @Override
    public List<Point> getCurrantField() {
        return this.field;
    }

    @Override
    public Point generateFood() {

        var foodOptional = field.stream()
                .filter(p -> p.getType() == PointType.FOOD)
                .findFirst();

        if (foodOptional.isPresent())
            return foodOptional.get();

        var empty = field.stream()
                .filter(p -> p.getType() == PointType.EMPTY)
                .collect(Collectors.toList());

        Random random = new Random();
        int index = random.nextInt(empty.size());

        Point food = empty.get(index);
        food.setType(PointType.FOOD);

        return food;
    }

    @Override
    public void mergeSnakeField(List<Point> snakePosition) {
        for (var i : field) {
            if (i.getType() == PointType.SNAKE)
                i.setType(PointType.EMPTY);
        }


        for (var i : snakePosition) {
            for (var j : field) {
                if (i.getColumn() == j.getColumn()
                    && i.getLine() == j.getLine())
                    j.setType(i.getType());
            }
        }
    }

}
