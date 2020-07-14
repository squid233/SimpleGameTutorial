package io.github.squid233.sgt.engine;

/**
 * @author squid233
 */
public class RenderThread implements Runnable {
    private Thread thread;
    /** 游戏是否退出 */
    private boolean exited = false;
    /** 每次绘制隔多久 */
    private final int interval;
    public Game game;

    public RenderThread(Game g) {
        game = g;
        //计算出隔多久重画一次(毫秒)
        interval = 1000 / game.getFps();
        System.out.println("[RenderThread]Created");
        System.out.println("[RenderThread]Render interval: " + interval + " ms");
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + "Start rendering");
        while (!exited) {
            game.repaint();//通知游戏窗口重画
            try {
                //间隔一定时间渲染一次，来实现稳定fps
                Thread.sleep(interval);
            } catch (Exception e) {
                System.out.println(thread.getName() + "Error: " + e.toString());
                exited = true;
            }
        }
        System.out.println(thread.getName() + "Stop rendering");
        game.exit();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "[RenderThread]");
            thread.start();
        }
    }

}