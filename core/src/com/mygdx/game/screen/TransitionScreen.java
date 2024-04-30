package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.transition.effects.TransitionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * 两界面之间的切换
 */
public class TransitionScreen extends BaseScreen {
    /**
     * 上一个界面
     */
    private BaseScreen current;
    /**
     * 下一个界面
     */
    private BaseScreen next;
    /**
     * 切换界面的特效集合
     */
    private List<TransitionEffect> transitionEffects;
    /**
     * 使用第几个特效
     */
    int currentTransitionEffect;

    /**
     * 构造器
     */
    public TransitionScreen(MyGdxGame game, BaseScreen current, BaseScreen next, ArrayList<TransitionEffect> transitionEffects) {
        this.gdxGame = game;
        this.current = current;
        this.next = next;
        this.transitionEffects = transitionEffects;
        this.currentTransitionEffect = 0;
    }

    @Override
    public void render(float delta) {
        //切换的不是选项界面，播放音乐
        if (next.getClass() != OptionScreen.class) {
            System.out.println("切换音乐");
        }
        //如果播放第N个超出特效集合，那就直接换过去
        if (currentTransitionEffect >= transitionEffects.size()) {
            gdxGame.setScreen(next);
            return;
        }
        //播放特效
        transitionEffects.get(currentTransitionEffect).update(delta);
        transitionEffects.get(currentTransitionEffect).render(current, next);
        //是否完成，完成了换下一个特效
        if (transitionEffects.get(currentTransitionEffect).isFinished()) {
            currentTransitionEffect ++;
        }
    }

    @Override
    public void show() {
        // Nothing
    }

    @Override
    public void resize(int width, int height) {
        // Nothing
    }

    @Override
    public void pause() {
        // Nothing
    }

    @Override
    public void resume() {
        // Nothing
    }

    @Override
    public void hide() {
        // Nothing
    }

    @Override
    public void dispose() {
        current.dispose();
        next.dispose();
    }
}
