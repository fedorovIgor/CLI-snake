package org.fedorovigord.model.point;

public enum PointType {

    SNAKE_HEAD {
        public String toString() {
            return "X";
        }
    },
    EMPTY {
        public String toString() {
            return " ";
        }
    },
    SNAKE {
        @Override
        public String toString() {
            return "*";
        }
    },
    FOOD {
        @Override
        public String toString() {
            return "@";
        }
    };
}
