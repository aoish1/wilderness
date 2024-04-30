package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.screen.MainMenuScreen;

/**
 * 起始点
 */
public class MyGdxGame extends Game {
    /**
     * 资源加载器
     */
    private ResourceManager resourceManager;

    /**
     * 主菜单界面
     */
    private MainMenuScreen menuScreen;


    @Override
    public void create() {
        resourceManager = new ResourceManager();
        menuScreen = new MainMenuScreen(this, resourceManager);
        this.setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        menuScreen.dispose();
        resourceManager.dispose();
    }
}
