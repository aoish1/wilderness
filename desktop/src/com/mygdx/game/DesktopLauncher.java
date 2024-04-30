package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

//请注意，在macOS上，您的应用程序需要使用-XstartOnFirstThread JVM参数启动
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280,920);
		config.setForegroundFPS(30);
		config.setTitle("荒野-测试");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
