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

    /**
     * 游戏的FPS
     */
    private int fps;

    public Game() {
        setFps(30);
        //初始化ArrayList
        gameObjects = new ArrayList<>();
        init();
        //初始化线程
        RenderThread render = new RenderThread(this);
        //启动线程
        render.start();
    }

    /**
     * 初始化窗口
     */
    private void init() {
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("My Simple Game " + VERSION);
        setVisible(true);
    }

    private void clear(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * 重写窗体绘制方法
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {
        clear(g);

        //渲染所有的game object
        for (GameObjectWithTexture gameObject : gameObjects) {
            gameObject.onTick();
            gameObject.draw(g);
        }
    }

    public void addGameObject(GameObjectWithTexture gameObject) {
        gameObjects.add(gameObject);
    }

    /**
     * 退出游戏
     */
    public void exit() {
        System.exit(-1);
    }

    /**
     * 返回游戏的FPS
     */
    public int getFps() {
        return fps;
    }

    /**
     * 设置游戏的FPS
     *
     * @param fps 新FPS
     * @return 是否设置成功
     */
    public boolean setFps(int fps) {
        if (fps <= 0) {
            return false;
        } else {
            this.fps = fps;
            return true;
        }
    }
}
