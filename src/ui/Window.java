package ui;

import model.Coord;
import model.Figures;
import service.FlyFigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    private Box[][] boxes;
    private FlyFigure fly;

    public Window() throws HeadlessException {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
    }

    public void addFigure() {
        fly = new FlyFigure();
        showFigure();
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Tetris Game");
        setLayout(null);
        setVisible(true);

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
        showFigure(1);
    }

    private void hideFigure() {
        showFigure(0);
    }

    private void showFigure( int color) {
        for (Coord dot : fly.getFigure().dots)
            setBoxColor(fly.getCoord().x + dot.x, fly.getCoord().y + dot.y, 1);
    }


    private void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }





    class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            hideFigure();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> fly.moveFigure(-1, 0);
                case KeyEvent.VK_RIGHT -> fly.moveFigure(1, 0);
                case KeyEvent.VK_DOWN -> fly.moveFigure(0, 1);
                case KeyEvent.VK_U -> fly.moveFigure(0, -1);
                case KeyEvent.VK_UP -> fly.turnFigure();
            }
            showFigure();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

}
