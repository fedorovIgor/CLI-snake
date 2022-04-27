package org.fedorovigord;

import org.fedorovigord.model.Point;
import org.fedorovigord.model.Snake;
import org.fedorovigord.model.point.PointImpl;
import org.fedorovigord.model.snake.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SnakeMoveTest {

    private Snake snake;

    private List<Point> afterMove;
    private List<Point> beforeMove;
    private SnakeDirection state;

    @BeforeEach
    public  void init() {
        snake = new SnakeImpl(3, 3);
    }


    @Test
    void shouldCreateSnake_inCorrectPosition() {
        beforeMove = List.of( new PointImpl(1,1), new PointImpl(1, 2));

        List<Point> afterInit = snake.getCurrantPoints();

        Assertions.assertEquals(beforeMove, afterInit);
    }

    @Test
    void should_turn_left() {
        afterMove = List.of( new PointImpl(1,0), new PointImpl(1, 1));

        snake.move();

        var answer = snake.getCurrantPoints();

        Assertions.assertEquals(afterMove, answer);
    }


    @Test
    void shouldNot_turn_right() {
        afterMove = List.of( new PointImpl(1,2), new PointImpl(1, 1));
        state = new Right();

        snake.setDirection(state);
        snake.move();

        var snakeCells = snake.getCurrantPoints();

        Assertions.assertNotEquals(afterMove, snakeCells);
    }

    @Test
    void should_turn_up() {
        afterMove = List.of( new PointImpl(0,1), new PointImpl(1, 1));

        state = new Up();

        snake.setDirection(state);
        snake.move();

        List<Point> snakeCells = snake.getCurrantPoints();

        Assertions.assertEquals(afterMove, snakeCells);
    }

    @Test
    void should_turn_down() {
        afterMove = List.of( new PointImpl(2,1), new PointImpl(1, 1));

        state = new Down();

        snake.setDirection(state);
        snake.move();

        List<Point> snakeCells = snake.getCurrantPoints();

        Assertions.assertEquals(afterMove, snakeCells);
    }


    @Test
    void shouldNotTurn_inIncorrectDirection() {
        state = new Down();

        snake.setDirection(state);

        state = new Up();
        snake.setDirection(state);

        System.out.println(snake.toString());

    }



    static void startSnake() {
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2);

        Snake snake = new SnakeImpl(3, 3);

        Runnable moveSnake = () -> {
            snake.move();

            System.out.println(snake.getCurrantPoints());
        };

        ScheduledFuture<?> moveSnakeHandel = scheduler
                .scheduleAtFixedRate(moveSnake, 0, 800, TimeUnit.MILLISECONDS);

        Runnable cancel = () -> moveSnakeHandel.cancel(false);

        scheduler.scheduleAtFixedRate(cancel, 10, 20, TimeUnit.SECONDS);
    }

}

