package org.fedorovigord.model.point;

import org.fedorovigord.model.Point;

import java.util.Objects;

public class PointImpl implements Point {
    private int line;
    private int column;

    private PointType type;



    public PointImpl(int line, int column) {
        this.line = line;
        this.column = column;

        this.type = PointType.EMPTY;
    }

    public PointImpl(Point point) {
        this.line = point.getLine();
        this.column = point.getColumn();

        this.type = PointType.EMPTY;
    }

    public PointImpl(int middleLine, int middleColumn, PointType type) {

        this.line = middleLine;
        this.column = middleColumn;

        this.type = type;
    }

    @Override
    public void shiftLeft() {
        this.column = column - 1;
    }
    @Override
    public void shiftRight() {
        this.column = column + 1;
    }
    @Override
    public void shiftUp() {
        this.line = line - 1;
    }
    @Override
    public void shiftDown() {
        this.line = line + 1;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }
    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public PointType getType() {
        return type;
    }

    @Override
    public void setType(PointType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointImpl point = (PointImpl) o;
        return line == point.line && column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }

    @Override
    public String toString() {
        return "Point{" +
                "line=" + line +
                ", column=" + column +
                ", type=" + type +
                '}';
    }
}
