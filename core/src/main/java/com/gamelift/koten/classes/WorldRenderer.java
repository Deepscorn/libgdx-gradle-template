package com.gamelift.koten.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gamelift.koten.entities.Dog;
import com.gamelift.koten.entities.Duck;

public class WorldRenderer {

	static final float FRUSTUM_WIDTH = 15;
	static final float FRUSTUM_HEIGHT = 10;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;
	ShapeRenderer shapeRenderer;
	private int sideX;

	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;

		shapeRenderer = new ShapeRenderer();
	}

	public void render() {
		clearScreen();

		cam.update();
		cam.unproject(world.touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
				0));
		batch.setProjectionMatrix(cam.combined);
		batch.enableBlending();

		if (world.dog.state == Dog.DOG_STATE_WALKING
				|| world.dog.state == Dog.DOG_STATE_FOUND
				|| world.dog.state == Dog.DOG_STATE_JUMPING
				|| world.dog.state == Dog.DOG_STATE_WALKING_NEW_ROUND) {
			renderBackground();
			renderObjects();
		} else {
			renderObjects();
			renderBackground();
		}
	}

	// also draws the background color
	private void clearScreen() {
		if (world.gameMode == World.GAME_MODE_1) {
			if (!(world.ducks.get(world.duckCount).state == Duck.DUCK_STATE_FLY_AWAY))
				Gdx.gl.glClearColor(0.392156f, 0.686274f, 1, 1);
			else
				Gdx.gl.glClearColor(1, 0.823529f, 0.3764705f, 1);
		} else {
			if (!(world.ducks.get(world.duckCount).state == Duck.DUCK_STATE_FLY_AWAY)
					|| !(world.ducks.get(world.duckCount + 1).state == Duck.DUCK_STATE_FLY_AWAY))
				Gdx.gl.glClearColor(0.392156f, 0.686274f, 1, 1);
			else
				Gdx.gl.glClearColor(1, 0.823529f, 0.3764705f, 1);
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void renderBackground() {
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2,
				cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT);
		batch.end();
	}

	private void renderObjects() {
		batch.begin();
		renderDog();
		renderDucks();
		batch.end();
	}

	private void renderDog() {
		if (world.dog.texture != null)
			batch.draw(world.dog.texture, world.dog.position.x,
					world.dog.position.y, Dog.DOG_WIDTH, Dog.DOG_HEIGHT);
	}

	private void renderDucks() {
		int len = world.ducks.size();
		for (int i = 0; i < len; i++) {
			Duck duck = world.ducks.get(i);

			TextureRegion texture = duck.texture;

			sideX = duck.velocity.x < 0 ? -1 : 1;
			if (texture != null)
				if (sideX < 0)
					batch.draw(texture, duck.position.x + Duck.DUCK_WIDTH / 2,
							duck.position.y - Duck.DUCK_HEIGHT / 2, sideX
									* Duck.DUCK_WIDTH, Duck.DUCK_HEIGHT);
				else
					batch.draw(texture, duck.position.x - Duck.DUCK_WIDTH / 2,
							duck.position.y - Duck.DUCK_HEIGHT / 2, sideX
									* Duck.DUCK_WIDTH, Duck.DUCK_HEIGHT);

			{// todo: Debug Duck
//				shapeRenderer.setProjectionMatrix(cam.combined);
//				shapeRenderer.begin(ShapeType.Line);
//				shapeRenderer.setColor(Color.RED);
//				shapeRenderer.rect(duck.bounds.x, duck.bounds.y,
//						duck.bounds.width, duck.bounds.height);
//				shapeRenderer.end();
			}
		}
	}
}
