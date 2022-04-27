package org.fedorovigord.model.point;

public enum PointType {
    EMPTY {
        public String toString() {
            return " ";
        }
    },
    SNAKE {
        @Override
        public String toString() {
            return "X";
        }
    },
    FOOD {
        @Override
        public String toString() {
            return "@";
        }
    };
}
