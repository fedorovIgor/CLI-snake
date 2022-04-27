package org.fedorovigord.controller;

import org.fedorovigord.model.Field;
import org.fedorovigord.model.Point;
import org.fedorovigord.model.Snake;
import org.fedorovigord.model.field.FieldImpl;
import org.fedorovigord.model.snake.*;
import org.fedorovigord.view.SnakeConsoleView;
import org.fedorovigord.view.SnakeView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameControllerImpl implements GameController {

    private Field field;
    private Snake snake;
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(3);
    private SnakeView view;

    private final Up up = new Up();
    private final Down down = new Down();
    private final Left left = new Left();
    private final Right right = new Right();



    public GameControllerImpl(int lineSize, int columnSize) {

        field = new FieldImpl(lineSize, columnSize);

        this.snake = new SnakeImpl(lineSize, columnSize);
        view = new SnakeConsoleView();
    }

    private boolean isGameOver() {
        return snake.isCrashed();
    }

    @Override
    public void setMoveDirection(String state) {

        state = state.toUpperCase();

        if (SnakeDirectionEnum.UP.direction.equals(state))
            snake.setDirection(up);
        if (SnakeDirectionEnum.DOWN.direction.equals(state))
            snake.setDirection(down);
        if (SnakeDirectionEnum.LEFT.direction.equals(state))
            snake.setDirection(left);
        if (SnakeDirectionEnum.RIGHT.direction.equals(state))
            snake.setDirection(right);

    }



    @Override
    public void start() {

        Point initFood = field.generateFood();
        snake.setFoodPoint(initFood);

        Runnable moveSnake = () -> {

            snake.move();

            Point food = field.generateFood();
            snake.setFoodPoint(food);

            field.mergeSnakeField(snake.getCurrantPoints());

            view.printFullField(field.getCurrantField());
        };

        ScheduledFuture<?> gameSchedule = scheduler.
                scheduleAtFixedRate(moveSnake, 0, 800, TimeUnit.MILLISECONDS);

        Runnable cancelGame = () -> {
            if (isGameOver()){
                gameSchedule.cancel(false);
                view.gameOverMessage(snake.getCurrantPoints());
                System.exit(1);
            }
        };

        scheduler.scheduleAtFixedRate(cancelGame, 400, 400, TimeUnit.MILLISECONDS);

        Scanner sc = new Scanner(System.in);

        Runnable scannerRun = () -> {
            var inp = sc.next();
            setMoveDirection(inp);
        };

        scheduler.scheduleAtFixedRate(scannerRun, 0, 500, TimeUnit.MILLISECONDS);
    }
}
