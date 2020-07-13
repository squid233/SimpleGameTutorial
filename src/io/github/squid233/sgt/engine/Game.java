package io.github.squid233.sgt.engine;

import io.github.squid233.sgt.obj.GameObjectWithTexture;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author squid233
 */
public class Game extends JFrame {

    private static final String VERSION = "1.0.0";

    //public static final int BORDER_TOP_SIZE_DEVIATION = 39;

    public static final int BORDER_TOP_SIZE = 30;
    public static final int BORDER_SIDE_SIZE = 8;

    private final ArrayList<GameObjectWithTexture> gameObjects;

    public Game() {
        //初始化ArrayList
        gameObjects = new ArrayList<>();
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (GameObjectWithTexture gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    public void addGameObject(GameObjectWithTexture gameObject) {
        gameObjects.add(gameObject);
    }
}
