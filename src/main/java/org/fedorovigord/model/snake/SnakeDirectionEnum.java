package org.fedorovigord.model.snake;

public enum SnakeDirectionEnum {
    UP("W"),
    DOWN("S"),
    LEFT("A"),
    RIGHT("D");

    public final String direction;

    private SnakeDirectionEnum(String label) {
        this.direction = label.toUpperCase();
    }
}
