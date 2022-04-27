package org.fedorovigord;

import org.fedorovigord.controller.GameController;
import org.fedorovigord.controller.GameControllerImpl;
import org.fedorovigord.model.snake.SnakeDirectionEnum;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A Camel Application
 */
public class MainApp {

    public static void main(String... args) {

        GameController game = new GameControllerImpl(5,5);

        game.start();
    }
}

