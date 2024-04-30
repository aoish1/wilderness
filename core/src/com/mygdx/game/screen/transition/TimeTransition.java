package com.mygdx.game.screen.transition;

public class TimeTransition {
    /**
     * 动画需要执行的时间
     */
    private float transitionTime;
    /**
     * 动画当前执行的时间
     */
    private float currentTime;
    /**
     * 是否完成
     */
    private boolean finished = true;
    /**
     * 是否启动
     */
    private boolean started = false;

    public boolean isFinished() {
        return finished;
    }

    /**
     * 返回当前时间占总时间的百分比
     */
    public float get() {
        if (transitionTime == 0) {
            return 1f;
        }
        return (currentTime / transitionTime);
    }

    public void start(float time) {
        this.transitionTime = time;
        this.currentTime = 0;
        this.finished = false;
        this.started = true;
    }

    public void stop() {
        this.started = false;
        this.finished = false;
    }

    public void update(float time) {
        if (!started) {
            return;
        }
        if (finished) {
            return;
        }
        this.currentTime += time;
        if (currentTime >= transitionTime) {
            currentTime = transitionTime;
            finished = true;
        }

    }
}
