package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.constant.ActionStatus;
import com.mygdx.game.constant.MapConstant;
import com.mygdx.game.input.GameInput;
import com.mygdx.game.npc.Player;
import com.mygdx.game.utils.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 游戏界面
 */
public class GameScreen extends BaseScreen {

    /**
     * 上一个场景
     */
    private final BaseScreen screen;
    /**
     * 画布
     */
    private final SpriteBatch batch;
    /**
     * 玩家
     */
    private final Player player;
    /**
     * 障碍物
     */
    private final List<Rectangle> obstacleList = new ArrayList<>();
    /**
     * 正交相机 忽略了其Z轴，大小不变  （2D用）
     */
    private final OrthographicCamera camera;
    /**
     * 相机渲染
     */
    private final OrthogonalTiledMapRenderer otRenderer;
    /**
     * 记录当前帧的时间
     */
    private float stateTime;
    /**
     * 记录上一帧的时间
     */
    private long lastFrameTime = System.nanoTime();
    /**
     * 帧数计数器
     */
    private float fpsCounter;

    /**
     * 构造器
     */
    public GameScreen(MyGdxGame gdxGame, BaseScreen screen, ResourceManager resourceManager) {
        this.gdxGame = gdxGame;
        this.screen = screen;
        this.resourceManager = resourceManager;
        //玩家初始化位置
        player = new Player();
        //批处理绘画
        batch = new SpriteBatch();
        //加载地图
        TmxMapLoader mapLoader = new TmxMapLoader();
        //地图
        TiledMap map = mapLoader.load(MapConstant.PATH);
        otRenderer = new OrthogonalTiledMapRenderer(map, MapConstant.UNIT_SCALE);
        // 获取地图中的图层
        MapLayer layer = map.getLayers().get(MapConstant.MAP_OBSTACLE_LEVEL);
        if (layer instanceof TiledMapTileLayer) {
            TiledMapTileLayer obstacleLayer = (TiledMapTileLayer) layer;
            // 遍历图层中的每个单元格，检查是否有障碍物
            for (int y = 0; y < obstacleLayer.getHeight(); y++) {
                for (int x = 0; x < obstacleLayer.getWidth(); x++) {
                    TiledMapTileLayer.Cell cell = obstacleLayer.getCell(x, y);
                    if (cell != null) {
                        TiledMapTile tile = cell.getTile();
                        if (tile != null) {
                            // 如果单元格中有障碍物，将其添加到障碍物数组中
                            float tileWidth = obstacleLayer.getTileWidth();
                            float tileHeight = obstacleLayer.getTileHeight();
                            float obstacleX = x * tileWidth;
                            float obstacleY = y * tileHeight;
                            obstacleList.add(new Rectangle(obstacleX * MapConstant.UNIT_SCALE, obstacleY * MapConstant.UNIT_SCALE, tileWidth * MapConstant.UNIT_SCALE, tileHeight * MapConstant.UNIT_SCALE));
                        }
                    }
                }
            }
        }
        //相机
        camera = new OrthographicCamera();
        camera.position.set(0, 100,0);
    }

    /**
     * 当此屏幕成为游戏的当前屏幕时调用
     */
    @Override
    public void show() {
        InputProcessor input = new GameInput();
        Gdx.input.setInputProcessor(input);
        fpsCounter = 0;
    }

    /**
     * 逐帧渲染
     */
    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
        player.update(stateTime);

        ScreenUtils.clear(0, 0, 0, 1);//设置背景色并清屏
        otRenderer.setView(camera);
        otRenderer.render();
        batch.setProjectionMatrix(camera.combined);//设置投影

        refreshFPS();//刷新FPS
        handleInput2();//相机移动
        handleInput();//玩家移动
        camera.update();

        batch.begin();
        player.render(batch);
        batch.end();
    }

    /**
     * 窗体大小改变时调用
     */
    @Override
    public void resize(int width, int height) {
        //调整窗口获得更多的世界，我们将看到宽度/32f单位。
        camera.viewportWidth = width / MapConstant.SQUARE_SIZE;
        camera.viewportHeight = camera.viewportWidth * height / width;
        //缩放
        float w = camera.viewportWidth * camera.zoom;
        float h = camera.viewportHeight * camera.zoom;
        //相机的初始位置
        camera.position.x = MathUtils.clamp(camera.position.x,w / 2, 100 - w / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, h / 2, 100 - h / 2);
        camera.position.z = 0;
        camera.update();
    }

    /**
     * 销毁
     */
    @Override
    public void dispose() {
        screen.dispose();
        batch.dispose();
    }

    /**
     * 更新帧率计数器
     */
    public void refreshFPS() {
        fpsCounter += Gdx.graphics.getDeltaTime();
        if (fpsCounter >= 1.0f) {
            // 计算帧率
            int fps = Gdx.graphics.getFramesPerSecond();
            // 更新标题显示帧率
            Gdx.graphics.setTitle("荒野-测试 - FPS: " + fps);
            // 重置帧率计数器
            fpsCounter = 0;
        }
    }

    /**
     * 玩家移动
     */
    private void handleInput() {
        //玩家坐标
        Rectangle playBox = player.getPlayBox();
        //每帧移动的长度
        long currentTime = System.nanoTime();//当前时间
        float deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0f; // 计算时间步长（秒），将纳秒转换为秒。
        float moveLength = player.getSpeed() * deltaTime;// 计算玩家应该移动的距离
        lastFrameTime = currentTime;//重置时间
        //动画的移动方向
        String move = ActionStatus.STOP;
        //移动指令
        Map<Integer, String> directionMap = GameInput.directionMap;
        Map.Entry<Integer, String> lastEntry = directionMap.entrySet().stream()
                .reduce((first, second) -> second)
                .orElse(null);
        if(lastEntry == null) {
            player.setPosition(playBox, move);
            return;
        }
        //玩家预移动坐标
        Rectangle playXY = new Rectangle(playBox);
        String value = lastEntry.getValue();
        if (ActionStatus.UP.equals(value)) {
            move = player.isRun() ? ActionStatus.UP_RUN : ActionStatus.UP_WALKING;
            playXY.y += moveLength;
        } else if (ActionStatus.BELOW.equals(value)) {
            move = player.isRun() ? ActionStatus.BELOW_RUN : ActionStatus.BELOW_WALKING;
            playXY.y -= moveLength;
        } else if (ActionStatus.LEFT.equals(value)) {
            move = player.isRun() ? ActionStatus.LEFT_RUN : ActionStatus.LEFT_WALKING;
            playXY.x -= moveLength;
        } else if (ActionStatus.RIGHT.equals(value)) {
            move = player.isRun() ? ActionStatus.RIGHT_RUN : ActionStatus.RIGHT_WALKING;
            playXY.x += moveLength;
        }
        System.out.println(playXY);
        //检测玩家和障碍物的碰撞
        for (Rectangle obstacle : obstacleList) {
            if (Intersector.overlaps(playXY, obstacle)) {
                player.setPosition(playBox, move);
                System.out.println("碰撞" + obstacle);
                return;
            }
        }
        player.setPosition(playXY, move);
    }

    /**
     * 相机移动
     */
    private void handleInput2() {
        //重置相机位置
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            camera.zoom = 1;
            camera.position.set(0, 0, 0);
            camera.rotate(0, 0, 0, 1);
        }
        //放大相机
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom += 0.2f;
        }
        //缩小相机
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.2f;
        }
        //左移相机
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-3, 0, 0);
        }
        //右移相机
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(3, 0, 0);
        }
        //下移相机
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -3, 0);
        }
        //上移相机
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, 3, 0);
        }
        //判定相机范围，不超出地图外
        camera.zoom = MathUtils.clamp(camera.zoom, 1f, MapConstant.MAP_WIDTH / camera.viewportWidth);
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
        float v1 = effectiveViewportWidth / 2f;
        float v2 = effectiveViewportHeight / 2f;
        camera.position.x = MathUtils.clamp(camera.position.x, v1, MapConstant.MAP_WIDTH - v1);
        camera.position.y = MathUtils.clamp(camera.position.y, v2, MapConstant.MAP_HEIGHT - v2);
    }
}
