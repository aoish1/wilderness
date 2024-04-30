package com.mygdx.game.constant;

/**
 * 地图常量
 */
public class MapConstant {

    /**
     * 玩家的初始位置
     */
    public final static float PLAYER_X = 0;
    /**
     * 玩家的初始位置
     */
    public final static float PLAYER_Y = 80.5f;
    /**
     * 地图上一个方块的大小
     */
    public final static float SQUARE_SIZE = 32f;
    /**
     * 地图宽 map_width 50 * 32 * 0.0625
     */
    public final static float MAP_WIDTH = 50 * 2;
    /**
     * 地图高 map_height 50 * 32 * 0.0625
     */
    public final static float MAP_HEIGHT = 50 * 2;

    /**
     * 展示的缩放 1 / 16
     */
    public final static float UNIT_SCALE = 0.0625f;
    /**
     * 玩家的缩放 32（像素） * 0.0625（缩放） * 0.75（人物大小）
     */
    public final static float PLAYER_SCALE = 1.5f;

    /**
     * 小型精灵占地面的大小 small
     */
    public final static float POKE_SIZE_SMALL = 32;
    /**
     * 中型精灵占地面的大小 medium
     */
    public final static float POKE_SIZE_MEDIUM = 32;
    /**
     * 大型精灵占地面的大小 large
     */
    public final static float POKE_SIZE_LARGE = 32;


    /**
     * 游戏地图 路径 map/sm.tmx
     */
    public static final String PATH = "map/pokemap.tmx";
    /**
     * 障碍物图层 Map_obstacle_level
     */
    public final static String MAP_OBSTACLE_LEVEL = "obstacle";
}
