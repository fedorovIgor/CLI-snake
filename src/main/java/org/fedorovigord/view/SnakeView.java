package org.fedorovigord.view;

import org.fedorovigord.model.Point;

import java.util.List;

public interface SnakeView {

    public void printFullField(List<Point> field);
    void gameOverMessage(List<Point> score);
}
