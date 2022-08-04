package ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable{

    public Window() throws HeadlessException {
    }

    private void initForm(){
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null );
        setVisible(true);
        setTitle("Tetris Game");
        setLocationRelativeTo(null);
    }
    private void initBoxes() {
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                Box box = new Box(x, y);
                add(box);
            }
    }

    @Override
    public void run() {
        initForm();
        initBoxes();
    }
}
