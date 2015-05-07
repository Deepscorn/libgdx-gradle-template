package com.gamelift.koten.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamelift.koten.classes.Assets;
import com.gamelift.koten.classes.DucksTextures;
import com.gamelift.koten.classes.DynamicGameObject;
import com.gamelift.koten.classes.World;

public class Duck extends DynamicGameObject {

	public static final int BLUE_DUCK = 0;
	public static final int BLACK_DUCK = 1;
	public static final int RED_DUCK = 2;

	public static final int DUCK_STATE_FLYING = 0;
	public static final int DUCK_STATE_HIT = 1;
	public static final int DUCK_STATE_FALLING = 2;
	public static final int DUCK_STATE_STANDBY = 3;
	public static final int DUCK_STATE_DEAD = 4;
	public static final int DUCK_STATE_FLY_AWAY = 6;
	public static final int DUCK_STATE_GONE = 7;
	public static final float DUCK_GRAVITY = -0.5f;
	public static final float DUCK_WIDTH = 1.25f;
	public static final float DUCK_HEIGHT = 1.25f;
	public static final int SCORE = 500;
	public static float duck_velocity_y = 6;
	public static float duck_velocity_x = 3;

	public int type;
	public TextureRegion texture;
	public TextureRegion uiTexture;
	public float side;
	public Integer state;
	public float uiStateTime;
	public float stateTime;
	private float lastTimeSaved;
	private float lastTimeSaved2;
	private Random rand;
	private int frames;
	private long soundID;

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		velocity.set(duck_velocity_x, duck_velocity_y);
		stateTime = 0;
		uiStateTime = 0;
		lastTimeSaved = 0;
		lastTimeSaved2 = 0;
		soundID = -1;
		rand = new Random();
		uiTexture = Assets.uiWhiteDuck;

		if (rand.nextFloat() > 0.5f)
			type = BLUE_DUCK;
		else if (rand.nextFloat() > 0.5f)
			type = BLACK_DUCK;
		else
			type = RED_DUCK;
	}

	public void update(float deltaTime) {

		switch (state) {
		case DUCK_STATE_STANDBY:
			uiTexture = Assets.uiWhiteDuck;
			texture = null;
			break;
		case DUCK_STATE_FLYING:
			stateFlying(deltaTime);
			uiStateFlying(deltaTime);
			checkStateTime();
			break;
		case DUCK_STATE_HIT:
			stateHit();
			break;
		case DUCK_STATE_FALLING:
			stateFalling(deltaTime);
			break;
		case DUCK_STATE_DEAD:
			// uiTexture = Assets.uiRedDuck;
			break;
		case DUCK_STATE_FLY_AWAY:
			stateFlyAway(deltaTime);
			break;
		case DUCK_STATE_GONE:
			stateGone();
			break;
		}

		stateTime += deltaTime;
	}

	private void stateFlying(float deltaTime) {
		texture = DucksTextures.getFlyingTexture(stateTime, type, velocity);

		if (position.y < 2.9f)
			velocity.y = Math.abs(velocity.y);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;

		if (rand.nextFloat() > 0.998f)
			velocity.x = -velocity.x;

		if (rand.nextFloat() > 0.998f)
			velocity.x = 0;

		if (rand.nextFloat() > 0.99f)
			velocity.x = duck_velocity_x;
		else if (rand.nextFloat() > 0.99f)
			velocity.x = -duck_velocity_x;

		if (rand.nextFloat() > 0.999f)
			velocity.y = -velocity.y;

		if (rand.nextFloat() > 0.999f)
			if (rand.nextFloat() > 0.99f)
				velocity.y = duck_velocity_y;
			else
				velocity.y = -duck_velocity_y;
		else if (rand.nextFloat() > 0.99f)
			if (rand.nextFloat() > 0.99f)
				velocity.y = duck_velocity_y / 3;
			else
				velocity.y = -duck_velocity_y / 3;
		else if (rand.nextFloat() > 0.99f)
			if (rand.nextFloat() > 0.99f)
				velocity.y = duck_velocity_y / 2;
			else
				velocity.y = -duck_velocity_y / 2;

		if (position.x < DUCK_WIDTH / 2) {
			position.x = DUCK_WIDTH / 2;
			velocity.x = duck_velocity_y;
			velocity.y = rand.nextFloat();
		}

		if (position.x > World.WORLD_WIDTH - DUCK_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - DUCK_WIDTH / 2;
			velocity.x = -duck_velocity_y;
			velocity.y = rand.nextFloat();
		}

		if (position.y < DUCK_WIDTH / 2) {
			position.y = DUCK_HEIGHT / 2;
			velocity.x = duck_velocity_y;
			velocity.y = rand.nextFloat();
		}

		if (position.y > World.WORLD_HEIGHT - DUCK_HEIGHT / 2) {
			position.y = World.WORLD_HEIGHT - DUCK_HEIGHT / 2;
			velocity.x = -duck_velocity_y;
			float topBot = rand.nextFloat() > 0.5f ? 1 : -1;
			velocity.y = rand.nextFloat() * topBot;
		}

		if (stateTime > 0.125f) {
			if ((stateTime - lastTimeSaved) >= 0.125f) {
				Assets.miss.play();
				lastTimeSaved = stateTime;
			}
		}

		if (stateTime > 1.3f) {
			if ((stateTime - lastTimeSaved2) >= 2f) {
				Assets.cuak.play();
				lastTimeSaved2 = stateTime;
			}
		}
	}

	private void uiStateFlying(float deltaTime) {
		if (uiStateTime < 1.5f)
			uiTexture = null;
		else if (uiStateTime < 3)
			uiTexture = Assets.uiWhiteDuck;
		else
			uiStateTime = 0;

		uiStateTime += deltaTime;
	}

	private void checkStateTime() {
		if (stateTime > 8f) {
			state = DUCK_STATE_FLY_AWAY;
			stateTime = 0;
			return;
		}
	}

	public void hit() {
		velocity.set(0, 0);
		state = Duck.DUCK_STATE_HIT;
		stateTime = 0;
	}

	public void dead() {
		/***/
	}

	private void stateFalling(float deltaTime) {
		frames++;
		if (frames > 4) {
			texture = DucksTextures.getFallingTexture(type, true);
			frames = 0;
		} else
			texture = DucksTextures.getFallingTexture(type, false);

		velocity.add(0, DUCK_GRAVITY);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DUCK_WIDTH / 2;
		bounds.y = position.y - DUCK_HEIGHT / 2;

		if (position.y < 2.7f) {
			state = DUCK_STATE_DEAD;
			Assets.duckFallingSnd.stop(soundID);
			Assets.hitGround.play();
		}
	}

	private void stateHit() {
		texture = DucksTextures.getHitTexture(type);
		uiTexture = Assets.uiRedDuck;

		if (stateTime > 1.0f) {
			state = DUCK_STATE_FALLING;
			velocity.set(0, DUCK_GRAVITY);
			soundID = Assets.duckFallingSnd.play();
		}
	}

	private void stateFlyAway(float deltaTime) {
		texture = DucksTextures.getUpTexture(type, stateTime);

		position.add(0, deltaTime * 5);

		if (stateTime > 0.125f) {
			if ((stateTime - lastTimeSaved) >= 0.125f) {
				Assets.miss.play();
				lastTimeSaved = stateTime;
			}
		}

		if (position.y > World.WORLD_HEIGHT + DUCK_HEIGHT) {
			state = DUCK_STATE_GONE;
		}

		uiTexture = Assets.uiWhiteDuck;
	}

	private void stateGone() {
		uiTexture = Assets.uiWhiteDuck;
	}
}
