package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.constant.PathConstant;
import com.mygdx.game.screen.transition.effects.TransitionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * 界面的基础类，包含了一些通用方法
 */
public class BaseScreen implements Screen {

    /**
     * 样式
     */
    protected Skin skin = new Skin(Gdx.files.internal(PathConstant.DEFAULT_SKIN));;
    /**
     * game窗口
     */
    protected MyGdxGame gdxGame;
    /**
     * 资源加载器
     */
    protected ResourceManager resourceManager;

    /**
     *
     * 设置切换界面
     */
    public void setScreenWithTransition(BaseScreen current, BaseScreen next, List<TransitionEffect> transitionEffect) {
        ArrayList<TransitionEffect> effects = new ArrayList<>(transitionEffect);
        Screen transitionScreen = new TransitionScreen(gdxGame, current, next, effects);
        gdxGame.setScreen(transitionScreen);
    }

    /**
     * 当此屏幕成为游戏的当前屏幕时调用
     */
    @Override
    public void show() {

    }

    /**
     * 逐帧渲染
     */
    @Override
    public void render(float delta) {

    }

    /**
     * 窗体大小改变时调用
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * 程序暂停时调用
     */
    @Override
    public void pause() {

    }

    /**
     * 程序恢复时调用
     */
    @Override
    public void resume() {

    }

    /**
     * 场景隐藏时调用
     */
    @Override
    public void hide() {

    }

    /**
     * 当此屏幕不是游戏的当前屏幕时调用
     */
    @Override
    public void dispose() {

    }
}
