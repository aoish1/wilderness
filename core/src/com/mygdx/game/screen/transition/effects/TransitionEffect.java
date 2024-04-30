package com.mygdx.game.screen.transition.effects;

import com.badlogic.gdx.Screen;
import com.mygdx.game.screen.transition.TimeTransition;

/**
 * 切换界面的效果（）
 */
public class TransitionEffect {

    private final TimeTransition timeTransition;

    public TransitionEffect(float duration) {
        timeTransition = new TimeTransition();
        timeTransition.start(duration);
    }

    protected float getAlpha() {
        return timeTransition.get();
    }

    public void update(float delta) {
        timeTransition.update(delta);
    }

    public void render(Screen current, Screen next) {
    }

    public boolean isFinished() {
        return timeTransition.isFinished();
    }

}
