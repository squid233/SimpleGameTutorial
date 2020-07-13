# SimpleGameTutorial

![java](https://img.shields.io/badge/language-java-red.svg)

这是一个简易的Java游戏制作教程。大部分的内容参考自[CSDN](https://blog.csdn.net/XcantloadX/article/details/87731020)

本教程每一章都会用tags来标记。

## 第一章：画出HelloWorld
我们首先创建Main类和Game类。Game类继承JFrame类。

Main类：

    package io.github.squid233.sgt;
    
    import io.github.squid233.sgt.engine.Game;

    /**
     * @author squid233
     */
    public class Main {
        public static void main(String[] args) {
            new Game();
        }
    }

Game类：

    package io.github.squid233.sgt.engine;
    
    import javax.swing.*;
    
    /**
     * @author squid233
     */
    public class Game extends JFrame {
    
        private static final String VERSION = "1.0.0";
    
        public Game() {
            setSize(800, 640);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("My Simple Game" + VERSION);
            setVisible(true);
        }
    
    }

其中`VERSION`可以更改为其它版本。

由于做的是游戏，不可能用Java自带的控件来做，那样效率很低，所以需要重写paint方法，自己来画。  
将Game类修改如下：

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
            setTitle("My Simple Game" + VERSION);
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

注意画文字的时候有个坑，坐标不要设置太小了，要不然会画在窗口外面，看不到。

效果图：  
![红背景与黑helloworld](result-image/helloworld.png)

## 第二章：画出一只苦力怕
为了更好地管理游戏，目前的框架是这样的：

Game：游戏的核心类，储存多个GameObject，处理游戏的渲染、输入等  
GameObjectWithTexture：一个显示出贴图的预制GameObject  
GameObject：最基本的类，游戏主要的逻辑处理

---
新建类GameObject，所有的游戏物体都会继承此类

    package io.github.squid233.sgt.obj;
    
    import java.awt.Graphics;
    
    /**
     * @author squid233
     */
    public class GameObject {
        /** 坐标x */
        protected int x;
        /** 坐标y */
        protected int y;
    
        /**
         * 渲染此GameObject
         *
         * @param g 目标Graphics
         */
        public void draw(Graphics g) {
    
        }
    
        /**
         * 设置坐标
         */
        public void setPosition(int x, int y) {
            setX(x);
            setY(y);
        }
    
        /**
         * 移动指定偏移坐标
         */
        public void transfer(int x, int y) {
            this.x += x;
            this.y += y;
        }
    
        public int getX() {
            return x;
        }
    
        public void setX(int x) {
            this.x = x;
        }
    
        public int getY() {
            return y;
        }
    
        public void setY(int y) {
            this.y = y;
        }

    }

再新建一个类GameObjectWithTexture(伴随着材质的GameObject)继承GameObject，重写draw方法

    package io.github.squid233.sgt.obj;
    
    import java.awt.Graphics;
    import java.awt.Image;
    import java.awt.Toolkit;
    
    /**
     * @author squid233
     */
    public class GameObjectWithTexture extends GameObject {
    
        private Image image;
    
        public GameObjectWithTexture(String filePath) {
            //从文件里加载图片
            image = Toolkit.getDefaultToolkit().createImage(filePath);
            this.x = 0;
            this.y = 0;
        }
    
        @Override
        public void draw(Graphics g) {
            //在(x,y)处绘制图片，不拉伸，原图片多大，画出来就多大
            g.drawImage(image, x, y, null);
        }
    
        public Image getImage() {
            return image;
        }
    
        public void setImage(Image image) {
            this.image = image;
        }
    }
---
为了能储存当前所有的GameObject，在Game类下创建一个ArrayList  
`private final ArrayList<GameObjectWithTexture> gameObjects;`

修改构造方法

    public Game() {
        //初始化ArrayList
        gameObjects = new ArrayList<>();
        init();
    }

添加addGameObject方法

    public void addGameObject(GameObjectWithTexture gameObject) {
        gameObjects.add(gameObject);
    }

在paint方法里需要遍历list，调用所有GameObject的draw方法，这样就能把所有的物体都显示出来

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (GameObjectWithTexture gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

这里提供一个素材（来自Minecraft）：![creeper](img/creeper.png)

修改main方法，加入一个GameObjectWithTexture

    Game game = new Game();
    GameObjectWithTexture creeper = new GameObjectWithTexture("img/creeper.png");
    creeper.setPosition(Game.BORDER_SIDE_SIZE, Game.BORDER_TOP_SIZE);
    game.addGameObject(creeper);

运行一下，效果图：  
![运行效果](result-image/yunxingxiaoguo.png)  
好像什么效果都没有？  
仔细查看代码，在创建Game对象的时候就已经创建了窗口

创建了之后窗口就要显示出来，paint方法会立即被调用，而此时GameObjectWithTexture还没有创建，所以什么都没有

现在将窗口最小化，再恢复，你会发现GameObjectWithTexture又显示出来了：  
![显示GameObjectWithTexture](result-image/chenggongxianshi.png)

因为窗口的状态被改变了，系统就通知窗口重画了一遍，所以GameObjectWithTexture显示了出来  
显然游戏不能这样，这是下一章里要解决的
