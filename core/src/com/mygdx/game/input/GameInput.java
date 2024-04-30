package com.mygdx.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.constant.ActionStatus;

import java.util.*;

/**
 * 游戏场景的事件处理
 */
public class GameInput implements InputProcessor {

    /**
     * 同一时间中，只能朝一个方向移动
     */
    public final static Map<Integer, String> directionMap = new LinkedHashMap<>();
    /**
     * 指挥宝可梦
     * 命令格式：选择宝可梦（1~6）、选择技能（1~4），就近攻击
     */
    private final List<Integer> commandList = new ArrayList<>();

    /**
     * 按下
     * false：未处理，true：已处理并释放该消息
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {//上移
            directionMap.put(keycode, ActionStatus.UP);
        } else if (keycode == Input.Keys.DOWN) {//下移
            directionMap.put(keycode, ActionStatus.BELOW);
        } else if (keycode == Input.Keys.LEFT) {//左移
            directionMap.put(keycode, ActionStatus.LEFT);
        } else if (keycode == Input.Keys.RIGHT) {//右移
            directionMap.put(keycode, ActionStatus.RIGHT);
        }
        return true;
    }

    /**
     * 松开
     */
    @Override
    public boolean keyUp(int keycode) {
        directionMap.remove(keycode);
        return true;
    }

    /**
     * 键入一个键时调用
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * 当屏幕被触摸或鼠标按钮被按下时
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * 当一个手指被取消或鼠标按钮被释放
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     *
     */
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * 当一个手指或鼠标被拖
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * 当鼠标滚轮滚动
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     *
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
