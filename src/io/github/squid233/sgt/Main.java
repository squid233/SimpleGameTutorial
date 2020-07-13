package io.github.squid233.sgt;

import io.github.squid233.sgt.engine.Game;
import io.github.squid233.sgt.obj.GameObjectWithTexture;

/**
 * @author squid233
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameObjectWithTexture creeper = new GameObjectWithTexture("img/creeper.png");
        creeper.setPosition(Game.BORDER_SIDE_SIZE, Game.BORDER_TOP_SIZE);
        game.addGameObject(creeper);
    }
}
