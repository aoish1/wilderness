package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * 资源管理器
 */
public class ResourceManager {

    /**
     * 文件路径解析工具
     */
    private static final InternalFileHandleResolver filePathResolver =  new InternalFileHandleResolver();

    /**
     * 选项屏幕
     */
    protected boolean isOptionScreen;
    /**
     * 菜单新游戏屏幕
     */
    protected boolean isMenuNewGameScreen;
    /**
     * 菜单加载游戏屏幕
     */
    protected boolean isMenuLoadGameScreen;
    /**
     * 地图纹理
     */
    public TextureAtlas atlas;

    /**
     * 资源加载器
     */
    private final AssetManager assetManager = new AssetManager();

    /**
     * 构造器，用来加载资源
     */
    public ResourceManager() {
        //加载纹理
//        assetManager.load("textures.atlas", TextureAtlas.class);
        //完成装载
//        assetManager.finishLoading();
        //获取纹理
//        atlas = assetManager.get("textures.atlas", TextureAtlas.class);
    }

    public void loadMapAsset(String mapFilenamePath) {
        if (mapFilenamePath == null || mapFilenamePath.isEmpty()) {
            return;
        }
        if (assetManager.isLoaded(mapFilenamePath)) {
            return;
        }
        if (filePathResolver.resolve(mapFilenamePath).exists() ) {
            assetManager.setLoader(TiledMap.class, new TmxMapLoader(filePathResolver));
            assetManager.load(mapFilenamePath, TiledMap.class);
            assetManager.finishLoadingAsset(mapFilenamePath);
            Gdx.app.debug("地图已加载！: {}", mapFilenamePath);
        } else {
            Gdx.app.debug("地图不存在！: {}", mapFilenamePath );
        }
    }

    public TiledMap getMapAsset(String mapFilenamePath) {
        TiledMap map = null;
        if (assetManager.isLoaded(mapFilenamePath)) {
            map = assetManager.get(mapFilenamePath, TiledMap.class);
        } else {
            Gdx.app.debug("未加载地图: {}", mapFilenamePath);
        }

        return map;
    }

    public void loadTextureAsset(String textureFilenamePath) {
        if (textureFilenamePath == null || textureFilenamePath.isEmpty()) {
            return;
        }
        if (assetManager.isLoaded(textureFilenamePath)) {
            return;
        }
        if (filePathResolver.resolve(textureFilenamePath).exists()) {
            assetManager.setLoader(Texture.class, new TextureLoader(filePathResolver));
            assetManager.load(textureFilenamePath, Texture.class);
            assetManager.finishLoadingAsset(textureFilenamePath);
        } else {
            Gdx.app.debug("纹理不存在！: {}", textureFilenamePath);
        }
    }

    public Texture getTextureAsset(String textureFilenamePath) {
        Texture texture = null;
        if (assetManager.isLoaded(textureFilenamePath)) {
            texture = assetManager.get(textureFilenamePath,Texture.class);
        } else {
            Gdx.app.debug("未加载纹理: {}", textureFilenamePath);
        }
        return texture;
    }

    public void loadMusicAsset(String musicFilenamePath) {
        if (musicFilenamePath == null || musicFilenamePath.isEmpty()) {
            return;
        }
        if (assetManager.isLoaded(musicFilenamePath)) {
            return;
        }
        //装载资产
        if (filePathResolver.resolve(musicFilenamePath).exists()) {
            assetManager.setLoader(Music.class, new MusicLoader(filePathResolver));
            assetManager.load(musicFilenamePath, Music.class);
            //在添加加载屏幕之前，只需阻止，直到加载地图
            assetManager.finishLoadingAsset(musicFilenamePath);
            Gdx.app.debug("音乐已加载！: {}", musicFilenamePath);
        } else {
            Gdx.app.debug("音乐不存在！: {}", musicFilenamePath);
        }
    }

    public Music getMusicAsset(String musicFilenamePath) {
        Music music = null;
        // 一旦资产管理器完成加载
        if (assetManager.isLoaded(musicFilenamePath)) {
            music = assetManager.get(musicFilenamePath, Music.class);
        } else {
            Gdx.app.debug("音乐未加载: {}", musicFilenamePath);
        }
        return music;
    }

    public boolean isOptionScreen() {
        return isOptionScreen;
    }

    public void setOptionScreen(boolean optionScreen) {
        isOptionScreen = optionScreen;
    }

    public boolean isMenuNewGameScreen() {
        return isMenuNewGameScreen;
    }

    public void setMenuNewGameScreen(boolean menuNewGameScreen) {
        isMenuNewGameScreen = menuNewGameScreen;
    }

    public boolean isMenuLoadGameScreen() {
        return isMenuLoadGameScreen;
    }

    public void setMenuLoadGameScreen(boolean menuLoadGameScreen) {
        isMenuLoadGameScreen = menuLoadGameScreen;
    }

    public boolean isAssetLoaded(String fileName) {
        return assetManager.isLoaded(fileName);
    }

    public void dispose() {
        assetManager.dispose();
//        atlas.dispose();
    }
}
