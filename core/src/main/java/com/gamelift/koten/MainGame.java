package com.gamelift.koten;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.gamelift.koten.classes.Assets;
import com.gamelift.koten.classes.Settings;
import com.gamelift.koten.screens.MainMenuScreen;

public class MainGame extends Game {
	FPSLogger fps;

	@Override
	public void create() {
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
		fps = new FPSLogger();
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}

	@Override
	public void render() {
		super.render();
		fps.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}