package com.gamelift.koten.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamelift.koten.classes.Assets;
import com.gamelift.koten.classes.GameObject;
import com.gamelift.koten.classes.World;

public class Dog extends GameObject {

	public static final int DOG_STATE_WALKING = 0;
	public static final int DOG_STATE_FOUND = 1;
	public static final int DOG_STATE_JUMPING = 2;
	public static final int DOG_STATE_FOUND_DUCK = 3;
	public static final int DOG_STATE_HIDDEN = 4;
	public static final int DOG_STATE_LAUGHING = 5;
	public static final int DOG_STATE_WALKING_NEW_ROUND = 6;
	public static final int DOG_STATE_LAUGHING_GAME_OVER = 7;
	public static final float DOG_WIDTH = 2f;
	public static final float DOG_HEIGHT = 2f;

	public TextureRegion texture;
	private int bark;
	public int state;
	public float stateTime;
	private int frames;
	private float dogPositiony;
	private int gravityY;
	private World world;

	public Dog(float x, float y, World world) {
		super(x, y, DOG_WIDTH, DOG_HEIGHT);
		state = DOG_STATE_WALKING;
		stateTime = 0;
		bark = 0;
		frames = -1;
		gravityY = -3;
		texture = Assets.dogWalking.getKeyFrame(stateTime, true);

		this.world = world;
	}

	public void update(float deltaTime, int ducksHit) {

		switch (state) {
		case DOG_STATE_WALKING:
			stateWalking(deltaTime);
			break;
		case DOG_STATE_WALKING_NEW_ROUND:
			stateWalkingNewRound(deltaTime);
			break;
		case DOG_STATE_FOUND:
			stateFound();
			break;
		case DOG_STATE_JUMPING:
			stateJumping(deltaTime);
			break;
		case DOG_STATE_FOUND_DUCK:
			stateFoundDuck(deltaTime, ducksHit);
			break;
		case DOG_STATE_LAUGHING:
			stateLaughing(deltaTime);
			break;
		case DOG_STATE_LAUGHING_GAME_OVER:
			stateLaughingGameOver(deltaTime);
			break;
		case DOG_STATE_HIDDEN:
			if (stateHidden(ducksHit))
				return;
			break;
		}

		stateTime += deltaTime;
	}

	private void stateWalking(float deltaTime) {
		if (stateTime > 6) {
			state = DOG_STATE_FOUND;
			stateTime = 0;
			return;
		}

		texture = Assets.dogWalking.getKeyFrame(stateTime, true);
		position.add(deltaTime, 0);
	}

	private void stateWalkingNewRound(float deltaTime) {
		if (position.x >= World.WORLD_WIDTH / 2 - World.WORLD_WIDTH / 5.5f) {
			state = DOG_STATE_FOUND;
			stateTime = 0;
			frames = -1;
			return;
		}

		texture = Assets.dogWalking.getKeyFrame(stateTime, true);
		position.add(deltaTime, 0);
	}

	private void stateFound() {
		if (stateTime > 0.4f) {
			state = DOG_STATE_JUMPING;
			dogPositiony = position.y;
			stateTime = 0;
			bark = 0;
		}
		texture = Assets.dogFound;
	}

	private void stateJumping(float deltaTime) {
		if (frames == -1)
			Assets.dogBark.play();

		frames++;
		if (frames > 15) {
			if (bark < 2) {
				Assets.dogBark.play();
				bark++;
			}
			frames = 0;
		}

		if (dogPositiony + 2 > position.y)
			position.add(deltaTime, deltaTime * 4);
		else {
			dogPositiony = 0;
			position.add(deltaTime, deltaTime * gravityY);
			gravityY += -1;
			if (position.y < 3) {
				state = DOG_STATE_HIDDEN;
			}
		}
		texture = Assets.dogJumping.getKeyFrame(stateTime);
	}

	private void stateFoundDuck(float deltaTime, int ducksHit) {
		if (stateTime < 0.21)
			position.add(0, deltaTime * 6);
		else if (stateTime < 0.5f) {
		} else
			position.add(0, -deltaTime * 6);

		if (stateTime > 1) {
			state = DOG_STATE_HIDDEN;
			world.state = World.WORLD_STATE_RUNNING;

			if (world.gameMode == World.GAME_MODE_2)
				world.duckCount += 2;
			else
				world.duckCount++;
		}

		if (ducksHit == 1)
			texture = Assets.dogDuckFound;
		else
			texture = Assets.dogDucksFound;

	}

	private void stateLaughing(float deltaTime) {
		if (stateTime < 0.5)
			position.add(0, deltaTime * 3);
		else if (stateTime < 1.3f) {
		} else
			position.add(0, -deltaTime * 6);

		if (stateTime > 3) {
			state = DOG_STATE_HIDDEN;
			world.state = World.WORLD_STATE_RUNNING;
			if (world.gameMode == World.GAME_MODE_2)
				world.duckCount += 2;
			else
				world.duckCount++;
		}

		texture = Assets.dogLaughing.getKeyFrame(stateTime, true);
	}

	private void stateLaughingGameOver(float deltaTime) {
		if (position.y <= 3.25f)
			position.add(0, deltaTime * 1);

		texture = Assets.dogLaughing.getKeyFrame(stateTime, true);
	}

	private boolean stateHidden(int ducksHit) {
		texture = null;

		if (world.state == World.WORLD_STATE_ROUND_PAUSE) {
			stateTime = 0;
			position.y = 1.7f;
			if (ducksHit == 0) {
				state = DOG_STATE_LAUGHING;
				Assets.dogLaughingSnd.play();
			} else {
				state = DOG_STATE_FOUND_DUCK;
				Assets.dogDuckFoundSnd.play();
			}
			return true;
		}
		return false;
	}
}
