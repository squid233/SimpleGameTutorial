package io.github.squid233.sgt.obj;

/**
 * @author squid233
 */
public class Creeper extends GameObjectWithTexture {

    public Creeper(String filePath) {
        super(filePath);
    }

    @Override
    public void onTick() {
        //在当前位置的基础上向右移动1个单位长度
        this.transfer(1, 0);
    }
}
