package com.gamelift.koten.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamelift.koten.MainGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
//        //generate items.atlas+png sheets from images from resource/image
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        settings.maxWidth = 512;
//        settings.maxHeight = 512;
//        TexturePacker.process(settings, "../../../../resource/image", "data", "items");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MainGame(), config);
    }
}

