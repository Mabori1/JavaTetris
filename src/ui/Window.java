package ui;

import model.Coord;
import model.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    private Box[][] boxes;
    private Figure figure;
    private Coord coord;

    public Window() throws HeadlessException {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
    }

    public void addFigure() {
        figure = Figure.getRandom();
        coord = new Coord(5, 5);
        showFigure();
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setTitle("Tetris Game");
        setLocationRelativeTo(null);
    }

    private void initBoxes() {
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                add(boxes[x][y]);
            }
    }

    @Override
    public void run() {
        repaint();
    }

    private void showFigure() {
        showFigure(figure, coord, 1);
    }

    private void hideFigure() {
        showFigure(figure, coord, 0);
    }

    private void showFigure(Figure figure, Coord at, int color) {
        for (Coord dot : figure.dots)
            setBoxColor(at.x + dot.x, at.y + dot.y, 1);
    }


    private void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }

    private boolean canMoveFigure(int sx, int sy) {
        int left = coord.x + sx + figure.top.x;
        if (left < 0) return false;
        int right = coord.x + sx + figure.bot.x;
        if (right >= Config.WIDTH) return false;
        else return true;
    }

    private void moveFigure(int sx, int sy) {
        if (canMoveFigure(sx, sy))
            coord = coord.plus(sx, sy);
    }

    class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            hideFigure();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> moveFigure(-1,0);
                case KeyEvent.VK_RIGHT -> moveFigure(1,0);
            }
            showFigure();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
