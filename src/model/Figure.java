package model;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    I1(0, 1, 1, 1, 2, 1, 3, 1),

    I2(1, 0,
            1, 1,
            1, 2,
            1, 3),

    J1(1, 0,
            1, 1,
            0, 2, 1, 2),

    J2(0, 0,
            0, 1, 1, 1, 2, 1),

    J3(1, 0, 2, 0,
            1, 1,
            1, 2),

    J4(0, 1, 1, 1, 2, 1,
            2, 2),

    L1(1, 0,
            1, 1,
            1, 2, 2, 2),

    L2(0, 1, 1, 1, 2, 1,
            0, 2),

    L3(0, 0, 1, 0,
            1, 1,
            1, 2),

    L4(2, 0,
            0, 1, 1, 1, 2, 1),

    O(0, 0, 1, 0,
            0, 1, 1, 1),

    S1(1, 1, 2, 1,
            0, 2, 1, 2),
    S2(0, 0,
            0, 1, 1, 1,
            1, 2),
    T1(0, 1, 1, 1, 2, 1,
            1, 2),

    T2(1, 0,
            0, 1, 1, 1,
            1, 2),
    T3(1, 0,
            0, 1, 1, 1, 2, 1),

    T4(1, 0,
            1, 1, 2, 1,
            1, 2),
    Z1(0, 1, 1, 1,
            1, 2, 2, 2),

    Z2(2, 0,
            1, 1, 2, 1,
            1, 2);

    public final List<Coord> dots;
    public final Coord top;
    public final Coord bot;

    private Figure(int... coords) {
        dots = new ArrayList<>();
        for (int j = 0; j < coords.length; j += 2)
            dots.add(new Coord(coords[j], coords[j + 1]));
        top = setTop();
        bot = setBot();
    }

    private Coord setBot() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coord coord : dots) {
            if (x < coord.x) x = coord.x;
            if (y < coord.y) y = coord.y;
        }
        return new Coord(x, y);

    }

    private Coord setTop() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coord coord : dots) {
            if (x > coord.x) x = coord.x;
            if (y > coord.y) y = coord.y;
        }
        return new Coord(x, y);
    }

    public Figure turnRight() {
        return switch (this) {
            case I1 -> I2;
            case I2 -> I1;
            case J1 -> J2;
            case J2 -> J3;
            case J3 -> J4;
            case J4 -> J1;
            case L1 -> L2;
            case L2 -> L3;
            case L3 -> L4;
            case L4 -> L1;
            case O -> O;
            case S1 -> S2;
            case S2 -> S1;
            case T1 -> T2;
            case T2 -> T3;
            case T3 -> T4;
            case T4 -> T1;
            case Z1 -> Z2;
            case Z2 -> Z1;
        };
    }

    public Figure turnLeft() {
        return turnRight().turnRight().turnRight();
    }

    public static Figure getRandom() {
        return Figure.values()[(int) (Math.random() * Figure.values().length)];
    }
}
