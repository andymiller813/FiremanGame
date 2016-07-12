package com.andytheandroidguy.firemangame.desktop;

import com.andytheandroidguy.firemangame.MainFireman;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MainFireman(), config);
        config.width = 1200;
        config.height = 624;
    }
}
