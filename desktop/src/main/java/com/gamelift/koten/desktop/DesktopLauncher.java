package com.gamelift.koten.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamelift.koten.MainGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        settings.maxWidth = 512;
//        settings.maxHeight = 512;
//        TexturePacker.process(settings, "src", "data", "items");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MainGame(), config);
    }
}

