package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.ResourceManager;

import java.util.ArrayList;

/**
 * 主菜单界面
 */
public class MainMenuScreen extends BaseScreen {

    /**
     * 菜单选项
     */
    String[] listName = {"开始游戏", "测试", "设置世界", "退出"};

    /**
     * 舞台
     */
    private final Stage menuStage;

    /**
     * 构造器
     */
    public MainMenuScreen(MyGdxGame gdxGame, ResourceManager resourceManager) {
        menuStage = new Stage();
        this.gdxGame = gdxGame;
        this.resourceManager = resourceManager;
        List<String> list = new List<>(skin);
        list.setItems(listName);
        // 如果不设大小，那么selection就不会显示，也不会响应click事件
        list.setSize(list.getPrefWidth(), list.getPrefHeight());
        list.setPosition(20, 20);

        list.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int selectedIndex = list.getSelectedIndex();
                if(selectedIndex == 0){
                    setScreenWithTransition((BaseScreen) gdxGame.getScreen(), new GameScreen(gdxGame, (BaseScreen) gdxGame.getScreen(), resourceManager), new ArrayList<>());
                }else if(selectedIndex == 1){
                    setScreenWithTransition((BaseScreen) gdxGame.getScreen(), new TestScreen(gdxGame, (BaseScreen) gdxGame.getScreen(), resourceManager), new ArrayList<>());
                }else if(selectedIndex == 3){
                    Gdx.app.exit();
                }
            }
        });
        Gdx.app.log("List", "width=" + list.getWidth() + "prefer width=" + list.getPrefWidth());
        Gdx.input.setInputProcessor(menuStage);
        menuStage.addActor(list);
    }

    /**
     * 当此屏幕成为游戏的当前屏幕时调用
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);
    }

    /**
     * 逐帧渲染
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.ROYAL);
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        menuStage.dispose();
    }
}
