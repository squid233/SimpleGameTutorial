package io.github.squid233.sgt.engine;

import javax.swing.*;
import java.awt.*;

/**
 * @author squid233
 */
public class Game extends JFrame {

    private static final String VERSION = "1.0.0";

    public static final int BORDER_TOP_SIZE = 39;
    public static final int BORDER_SIDE_SIZE = 7;

    public Game() {
        init();
    }

    /**
     * 初始化窗口
     */
    public void init() {
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("My Simple Game " + VERSION);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawString("helloworld",  BORDER_SIDE_SIZE, BORDER_TOP_SIZE);
    }
}
