package org.fedorovigord.view;

import org.fedorovigord.controller.GameController;
import org.fedorovigord.model.Point;

import java.util.List;

public class SnakeConsoleView implements SnakeView {

    public SnakeConsoleView() {

    }

    @Override
    public void printFullField(List<Point> field) {
        StringBuilder sb = new StringBuilder();

        int lineSize =  field.stream()
                .mapToInt(p -> p.getLine())
                .max()
                .getAsInt() + 1;

        int columnSize =  field.stream()
                .mapToInt(p -> p.getColumn())
                .max()
                .getAsInt() + 1;

        Point[][] points = new Point[lineSize][columnSize];


        for (var i : field) {
            points[i.getLine()][i.getColumn()] = i;
        }

        for (int i = 0; i < lineSize; i++) {
            sb.append("|");

            for (int j = 0; j < columnSize; j++) {

                sb.append(points[i][j].getType());
                sb.append("|");
            }

            sb.append("\n");
        }

        System.out.println(sb);

        System.out.println("");
    }

    @Override
    public void gameOverMessage(List<Point> points) {

        System.out.println("Game over! Your score: %s"
                .formatted(points.size()));
    }

}
