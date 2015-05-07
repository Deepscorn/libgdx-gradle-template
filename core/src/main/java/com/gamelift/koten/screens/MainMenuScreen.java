package com.gamelift.koten.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.gamelift.koten.classes.Assets;
import com.gamelift.koten.classes.Settings;
import com.gamelift.koten.classes.World;

public class MainMenuScreen implements Screen {

	Game game;

	int state;
	OrthographicCamera guiCam;
	Rectangle playMode1Bounds;
	Rectangle playMode2Bounds;
	Rectangle soundBounds;
	Vector3 touchPoint;
	SpriteBatch batcher;
	int menuCursor;

	public MainMenuScreen(Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(480, 320);
		guiCam.position.set(480 / 2, 320 / 2, 0);
		playMode1Bounds = new Rectangle(480 / 2
				- Assets.gameMode1.getRegionWidth() / 2 - 60, 100,
				Assets.gameMode1.getRegionWidth() * 2,
				Assets.gameMode1.getRegionHeight() * 2);
		playMode2Bounds = new Rectangle(480 / 2
				- Assets.gameMode2.getRegionWidth() / 2 - 60, 70,
				Assets.gameMode2.getRegionWidth() * 2,
				Assets.gameMode2.getRegionHeight() * 2);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		menuCursor = 100;

		Assets.duckHunt.play();
	}

	public void update(float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			if (playMode1Bounds.contains(touchPoint.x, touchPoint.y)) {
				if (menuCursor == 100) {
					game.setScreen(new GameScreen(game, World.GAME_MODE_1));
					if (Assets.duckHunt.isPlaying())
						Assets.duckHunt.stop();
				} else {
					menuCursor = 100;
					Assets.playSound(Assets.shoot);
				}
				return;
			}

			if (playMode2Bounds.contains(touchPoint.x, touchPoint.y)) {
				if (menuCursor == 70) {
					game.setScreen(new GameScreen(game, World.GAME_MODE_2));
					if (Assets.duckHunt.isPlaying())
						Assets.duckHunt.stop();
				} else {
					menuCursor = 70;
					Assets.playSound(Assets.shoot);
				}
				return;
			}
		}
	}

	public void draw(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		presentMenu();
	}

	private void presentMenu() {
		batcher.enableBlending();
		batcher.begin();

		batcher.draw(Assets.title, 480 / 2 - Assets.title.getRegionWidth() / 2,
				320 / 2, Assets.title.getRegionWidth(),
				Assets.title.getRegionHeight());
		batcher.draw(Assets.gameMode1,
				480 / 2 - Assets.gameMode1.getRegionWidth() / 2 - 60, 100,
				Assets.gameMode1.getRegionWidth() * 2,
				Assets.gameMode1.getRegionHeight() * 2);
		batcher.draw(Assets.gameMode2,
				480 / 2 - Assets.gameMode2.getRegionWidth() / 2 + 4 - 60, 70,
				Assets.gameMode2.getRegionWidth() * 2,
				Assets.gameMode2.getRegionHeight() * 2);
		batcher.draw(Assets.menuCursor,
				480 / 2 - Assets.gameMode2.getRegionWidth() / 2 - 80,
				menuCursor, Assets.menuCursor.getRegionWidth() * 2,
				Assets.menuCursor.getRegionHeight() * 2);

		//Assets.font.setScale(0.5f, 0.5f);
		Assets.font.draw(batcher, "High Score ",
				480 / 2 - Assets.gameMode2.getRegionWidth() / 2 - 40, 40);
		Assets.font.draw(batcher, String.valueOf(Settings.highscore), 480 / 2
				- Assets.gameMode2.getRegionWidth() / 2 + 100, 40);

		batcher.end();
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}