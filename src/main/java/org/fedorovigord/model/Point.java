package org.fedorovigord.model;

import org.fedorovigord.model.point.PointType;

public interface Point {
    void shiftLeft();

    void shiftRight();

    void shiftUp();

    void shiftDown();

    void setLine(int line);

    void setColumn(int column);

    int getLine();

    int getColumn();

    PointType getType();

    void setType(PointType type);
}
