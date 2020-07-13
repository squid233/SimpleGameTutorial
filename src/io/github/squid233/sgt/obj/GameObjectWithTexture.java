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
