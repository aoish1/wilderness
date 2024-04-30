package com.mygdx.game.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.constant.ActionStatus;
import com.mygdx.game.constant.MapConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩家类
 */
public class Player {
    /**
     * 行动状态
     */
    private String status;
    /**
     * 动画帧
     */
    private float stateTime;
    /**
     * 玩家（检查碰撞）
     */
    private final Rectangle playBox;
    /**
     * 动画集合
     */
    private final Map<String,Animation<TextureRegion>> animationMap;
    /**
     * 玩家移动速度（像素）
     * 每秒移动100像素
     */
    private final Float speed = 10.0f;
    /**
     * 是否奔跑
     */
    private final Boolean run;

    /**
     * 构造器
     */
    public Player() {
        run = false;
        status = ActionStatus.IDLE;
        animationMap = new HashMap<>();
        //玩家初始化位置
        playBox = new Rectangle(MapConstant.PLAYER_X, MapConstant.PLAYER_Y, MapConstant.PLAYER_SCALE, MapConstant.PLAYER_SCALE);
        //玩家纹理图像
        Texture playerTexture = new Texture("player/red/walking.png");
        int rows = 1;
        int cols = 8;
        TextureRegion[][] actions = TextureRegion.split(playerTexture, playerTexture.getWidth() / cols, playerTexture.getHeight() / rows);
        //走路
        TextureRegion[] below_walking = new TextureRegion[2];
        below_walking[0] = actions[0][0];
        below_walking[1] = actions[0][4];
        animationMap.put(ActionStatus.BELOW_WALKING,new Animation<>(0.1f, below_walking));
        TextureRegion[] up_walking = new TextureRegion[2];
        up_walking[0] = actions[0][1];
        up_walking[1] = actions[0][5];
        animationMap.put(ActionStatus.UP_WALKING,new Animation<>(0.1f, up_walking));
        TextureRegion[] left_walking = new TextureRegion[2];
        left_walking[0] = actions[0][2];
        left_walking[1] = actions[0][6];
        animationMap.put(ActionStatus.LEFT_WALKING,new Animation<>(0.1f, left_walking));
        TextureRegion[] right_walking = new TextureRegion[2];
        right_walking[0] = actions[0][3];
        right_walking[1] = actions[0][7];
        animationMap.put(ActionStatus.RIGHT_WALKING,new Animation<>(0.1f, right_walking));
        //停止
        TextureRegion[] below_stop = new TextureRegion[1];
        below_stop[0] = actions[0][0];
        animationMap.put(ActionStatus.BELOW_STOP,new Animation<>(0.1f, below_stop));
        TextureRegion[] up_stop = new TextureRegion[1];
        up_stop[0] = actions[0][1];
        animationMap.put(ActionStatus.UP_STOP,new Animation<>(0.1f, up_stop));
        TextureRegion[] left_stop = new TextureRegion[1];
        left_stop[0] = actions[0][2];
        animationMap.put(ActionStatus.LEFT_STOP,new Animation<>(0.1f, left_stop));
        TextureRegion[] right_stop = new TextureRegion[1];
        right_stop[0] = actions[0][3];
        animationMap.put(ActionStatus.RIGHT_STOP,new Animation<>(0.1f, right_stop));
        //空闲
        TextureRegion[] idle = new TextureRegion[2];
        idle[0] = actions[0][0];
        idle[1] = actions[0][0];
        animationMap.put(ActionStatus.IDLE,new Animation<>(0.1f, idle));
    }

    /**
     * 这里可以处理玩家的移动逻辑
     */
    public void update(float deltaTime) {
        // 更新动画的状态时间
        stateTime += deltaTime;
    }

    /**
     * 重新绘制
     */
    public void render(SpriteBatch batch) {
        Animation<TextureRegion> playerAnimation = animationMap.get(status);
        // 获取当前动画帧
        TextureRegion currentFrame = playerAnimation.getKeyFrame(stateTime, true);
        // 绘制玩家动画
        batch.draw(currentFrame,playBox.x,playBox.y, MapConstant.PLAYER_SCALE,MapConstant.PLAYER_SCALE);
    }

    /**
     * 玩家移动
     */
    public void setPosition(Rectangle playBox, String move) {
        this.playBox.x = playBox.x;
        this.playBox.y = playBox.y;
        //展示的动画
        if(ActionStatus.STOP.equals(move)) {
            status = status.replaceFirst("_(.*)", ActionStatus.STOP);
        }else {
            status = move;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public Rectangle getPlayBox() {
        return playBox;
    }

    public String getStatus() {
        return status;
    }

    public boolean isRun() {
        return run;
    }
}
