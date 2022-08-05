package service;

import model.Coord;
import model.Figures;
import model.Mapable;
import ui.Config;

public class FlyFigure {

    private Figures figure;

    private Coord coord;
    private boolean lended;
    private int ticks;
    private Mapable map;

    public FlyFigure(Mapable map) {
        this.map = map;
        figure = Figures.getRandom();
        coord = new Coord(Config.WIDTH / 2 - 2, -figure.top.y - figure.bot.y - 1);
        lended = false;
        ticks = 2;
    }

    public boolean isLanded() {
        return lended;

    }

    public Coord getCoord() {
        return coord;
    }

    public Figures getFigure() {
        return figure;
    }

    public boolean canPlaceFigure(){
        return canMoveFigure(figure, 0,0);
    }

    private boolean canMoveFigure(Figures figure, int sx, int sy) {
        if (coord.x + sx + figure.top.x < 0) return false;
        if (coord.x + sx + figure.bot.x >= Config.WIDTH) return false;
        //if (coord.y + sy + figure.top.y < 0) return false;
        if (coord.y + sy + figure.bot.y >= Config.HEIGHT) return false;
        for (Coord coord1 : figure.dots)
            if (map.getBoxColor(coord1.x + coord.x + sx, coord1.y + coord.y + sy) == 0)
                return false;
        return true;
    }

    public void moveFigure(int sx, int sy) {
        if (canMoveFigure(figure, sx, sy))
            coord = coord.plus(sx, sy);
        else if (sy == 1) {
            if (ticks > 0) ticks--;
            else lended = true;
        }
    }

    public void turnFigure(int direction) {
        Figures rotate = direction == 1 ? figure.turnRight() : figure.turnLeft();
        if (canMoveFigure(rotate, 0, 0))
            figure = rotate;
        else if (canMoveFigure(rotate, 1, 0)) {
            figure = rotate;
            moveFigure(1, 0);
        } else if (canMoveFigure(rotate, -1, 0)) {
            figure = rotate;
            moveFigure(-1, 0);
        } else if (canMoveFigure(rotate, 0, -1)) {
            figure = rotate;
            moveFigure(0, -1);
        }
    }
}
