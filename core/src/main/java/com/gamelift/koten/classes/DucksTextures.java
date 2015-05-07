package com.gamelift.koten.classes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gamelift.koten.entities.Duck;

public class DucksTextures {

	public static TextureRegion getFlyingTexture(float stateTime, int type,
			Vector2 velocity) {
		TextureRegion texture = null;

		switch (type) {
		case Duck.BLUE_DUCK:
			texture = getBlueDuckTexture(stateTime, velocity);
			break;
		case Duck.BLACK_DUCK:
			texture = getBlackDuckTexture(stateTime, velocity);
			break;
		case Duck.RED_DUCK:
			texture = getRedDuckTexture(stateTime, velocity);
			break;
		}

		return texture;
	}

	private static TextureRegion getRedDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x == 0)
			texture = Assets.duckFlyUpRed.getKeyFrame(stateTime, true);
		else if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightRed.getKeyFrame(stateTime, true);
		else if (velocity.x < velocity.y)
			texture = Assets.duckFlyTopRed.getKeyFrame(stateTime, true);
		else
			texture = Assets.duckFlyTopRed.getKeyFrame(stateTime, true);

		return texture;
	}

	private static TextureRegion getBlackDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x == 0)
			texture = Assets.duckFlyUpBlack.getKeyFrame(stateTime, true);
		else if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightBlack.getKeyFrame(stateTime, true);
		else if (velocity.x < velocity.y)
			texture = Assets.duckFlyTopBlack.getKeyFrame(stateTime, true);
		else
			texture = Assets.duckFlyTopBlack.getKeyFrame(stateTime, true);

		return texture;
	}

	private static TextureRegion getBlueDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x == 0)
			texture = Assets.duckFlyUpBlue.getKeyFrame(stateTime, true);
		else if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightBlue.getKeyFrame(stateTime, true);
		else if (velocity.x < velocity.y)
			texture = Assets.duckFlyTopBlue.getKeyFrame(stateTime, true);
		else
			texture = Assets.duckFlyTopBlue.getKeyFrame(stateTime, true);

		return texture;
	}

	public static TextureRegion getFallingTexture(int type, boolean flip) {
		TextureRegion texture = null;

		switch (type) {
		case Duck.BLUE_DUCK:
			Assets.duckFallingBlue.flip(flip, false);
			texture = Assets.duckFallingBlue;
			break;
		case Duck.BLACK_DUCK:
			Assets.duckFallingBlack.flip(flip, false);
			texture = Assets.duckFallingBlack;
			break;
		case Duck.RED_DUCK:
			Assets.duckFallingRed.flip(flip, false);
			texture = Assets.duckFallingRed;
			break;
		}

		return texture;
	}

	public static TextureRegion getHitTexture(int type) {
		TextureRegion texture = null;

		switch (type) {
		case Duck.BLUE_DUCK:
			texture = Assets.duckHitBlue;
			break;
		case Duck.BLACK_DUCK:
			texture = Assets.duckHitBlack;
			break;
		case Duck.RED_DUCK:
			texture = Assets.duckHitRed;
			break;
		}

		return texture;
	}

	public static TextureRegion getUpTexture(int type, float stateTime) {
		TextureRegion texture = null;

		switch (type) {
		case Duck.BLUE_DUCK:
			texture = Assets.duckFlyUpBlue.getKeyFrame(stateTime, true);
			break;
		case Duck.BLACK_DUCK:
			texture = Assets.duckFlyUpBlack.getKeyFrame(stateTime, true);
			break;
		case Duck.RED_DUCK:
			texture = Assets.duckFlyUpRed.getKeyFrame(stateTime, true);
			break;
		}

		return texture;
	}
}
