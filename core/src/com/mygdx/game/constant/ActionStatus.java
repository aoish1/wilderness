package com.mygdx.game.constant;

/**
 * 行为状态
 */
public class ActionStatus {
    /**
     * 停止当前动作
     */
    public final static String STOP = "_stop";
    /**
     * 空闲
     */
    public final static String IDLE = "player" + STOP;

    /**
     * 向上标识
     */
    public final static String UP = "up";
    /**
     * 向下标识
     */
    public final static String BELOW = "below";
    /**
     * 向左标识
     */
    public final static String LEFT = "left";
    /**
     * 向右标识
     */
    public final static String RIGHT = "right";

    /**
     * 向上-静止
     */
    public final static String UP_STOP = UP + "_stop";
    /**
     * 向下-静止
     */
    public final static String BELOW_STOP = BELOW + "_stop";
    /**
     * 向左-静止
     */
    public final static String LEFT_STOP = LEFT + "_stop";
    /**
     * 向右-静止
     */
    public final static String RIGHT_STOP = RIGHT + "_stop";

    /**
     * 向上-走路
     */
    public final static String UP_WALKING = UP + "_walking";
    /**
     * 向下-走路
     */
    public final static String BELOW_WALKING = BELOW + "_walking";
    /**
     * 向左-走路
     */
    public final static String LEFT_WALKING = LEFT + "_walking";
    /**
     * 向右-走路
     */
    public final static String RIGHT_WALKING = RIGHT + "_walking";

    /**
     * 向上-奔跑
     */
    public final static String UP_RUN = UP + "_run";
    /**
     * 向下-奔跑
     */
    public final static String BELOW_RUN = BELOW + "_run";
    /**
     * 向左-奔跑
     */
    public final static String LEFT_RUN = LEFT + "_run";
    /**
     * 向右-奔跑
     */
    public final static String RIGHT_RUN = RIGHT + "_run";
}
