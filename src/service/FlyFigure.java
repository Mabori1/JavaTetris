package service;

import model.Coord;
import model.Figures;
import ui.Config;

public class FlyFigure {

    private Figures figure;

    private Coord coord;


    public Coord getCoord() {
        return coord;
    }
    public Figures getFigure() {
        return figure;
    }

    public FlyFigure(){
        figure = Figures.getRandom();
        coord = new Coord(Config.WIDTH/2 -2, -figure.top.y - figure.bot.y -1);
    }

    private boolean canMoveFigure(Figures figure, int sx, int sy) {
        if (coord.x + sx + figure.top.x < 0) return false;
        if (coord.x + sx + figure.bot.x >= Config.WIDTH) return false;
        //if (coord.y + sy + figure.top.y < 0) return false;
        if (coord.y + sy + figure.bot.y >= Config.HEIGHT) return false;
        return true;
    }
    public void moveFigure(int sx, int sy) {
        if (canMoveFigure(figure, sx, sy))
            coord = coord.plus(sx, sy);
    }
    public void turnFigure() {
        Figures rotate = figure.turnRight();
        if (canMoveFigure(rotate, 0, 0))
            figure = rotate;
        else if (canMoveFigure(rotate, 1, 0)) {
            figure = rotate;
            moveFigure(1, 0);
        } else if (canMoveFigure(rotate, -1, 0)) {
            figure = rotate;
            moveFigure(-1, 0);
        }else
        if (canMoveFigure(rotate, 0,-1)) {
            figure = rotate;
            moveFigure(0, -1);
        }
    }



}
