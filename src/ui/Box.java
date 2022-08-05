package ui;

import javax.swing.*;

public class Box extends JPanel {
    private int color;

    public int getColor() {
        return color;
    }

    public Box(int x, int y) {
        color = 0;
        setBounds(x * Config.SIZE, y * Config.SIZE, Config.SIZE, Config.SIZE);
        setBackground(Config.BACK);
        setVisible(true);
    }

    public void setColor(int color) {
        this.color = color;
        if (color == 0) setBackground(Config.BACK);
        else setBackground(Config.FORE);
    }

}
