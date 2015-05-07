package com.gamelift.koten.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
	public static TextureAtlas items;

	public static TextureRegion backgroundRegion;
	public static TextureRegion pause;
	public static TextureRegion ready;
	public static TextureRegion duckHit;
	public static TextureRegion title;
	public static TextureRegion gameMode1;
	public static TextureRegion gameMode2;
	public static TextureRegion menuCursor;
	public static TextureRegion dogFound;
	public static TextureRegion uiShot;
	public static TextureRegion uiDucksRound;
	public static TextureRegion uiScore;
	public static TextureRegion presentRound;
	public static TextureRegion ui3Shots;
	public static TextureRegion ui2Shots;
	public static TextureRegion ui1Shots;
	public static TextureRegion uiWhiteDuck;
	public static TextureRegion uiRedDuck;
	public static TextureRegion dogDuckFound;
	public static TextureRegion dogDucksFound;
	public static TextureRegion presentFlyAway;
	public static TextureRegion ui0Shots;
	public static TextureRegion duckFallingBlue;
	public static TextureRegion duckFallingBlack;
	public static TextureRegion duckFallingRed;
	public static TextureRegion duckHitBlue;
	public static TextureRegion duckHitBlack;
	public static TextureRegion duckHitRed;

	public static Animation duckFlyRightBlue;
	public static Animation duckFlyRightBlack;
	public static Animation duckFlyRightRed;
	public static Animation duckFlyTopBlue;
	public static Animation duckFlyTopBlack;
	public static Animation duckFlyTopRed;
	public static Animation duckFlyUpBlue;
	public static Animation duckFlyUpBlack;
	public static Animation duckFlyUpRed;
	public static Animation dogWalking;
	public static Animation dogJumping;
	public static Animation dogLaughing;
	public static Animation uiDucks;

	public static BitmapFont font;

	public static Music duckHunt;
	public static Music startRound;
	public static Music endRound;
	public static Music gameOver1;
	public static Music gameOver2;

	public static Sound shoot;
	public static Sound dogBark;
	public static Sound miss;
	public static Sound dogLaughingSnd;
	public static Sound dogDuckFoundSnd;
	public static Sound hitGround;
	public static Sound duckFallingSnd;
	public static Sound cuak;
	public static Sound movingDucksArray;
	public static Sound perfect;

	public static void load() {
		loadAtlas();

		loadTextures();

		loadFont();

		loadSounds();
	}

	private static void loadAtlas() {
		items = new TextureAtlas(Gdx.files.internal("data/items.pack"),
				Gdx.files.internal("data"));
	}

	private static void loadTextures() {
		backgroundRegion = items.findRegion("background");
		dogWalking = new Animation(0.15f,
				((TextureRegion) items.findRegion("dogWalking1")),
				((TextureRegion) items.findRegion("dogWalking2")),
				((TextureRegion) items.findRegion("dogWalking3")));
		dogFound = items.findRegion("dogFound");
		dogJumping = new Animation(0.8f,
				((TextureRegion) items.findRegion("dogJump1")),
				((TextureRegion) items.findRegion("dogJump2")));
		dogLaughing = new Animation(0.1f,
				((TextureRegion) items.findRegion("dogLaugh1")),
				((TextureRegion) items.findRegion("dogLaugh2")));
		duckFlyRightBlue = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyRightBlue1")),
				((TextureRegion) items.findRegion("duckFlyRightBlue2")),
				((TextureRegion) items.findRegion("duckFlyRightBlue3")));
		duckFlyRightBlack = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyRightBlack1")),
				((TextureRegion) items.findRegion("duckFlyRightBlack2")),
				((TextureRegion) items.findRegion("duckFlyRightBlack3")));
		duckFlyRightRed = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyRightRed1")),
				((TextureRegion) items.findRegion("duckFlyRightRed2")),
				((TextureRegion) items.findRegion("duckFlyRightRed3")));
		duckFlyTopBlue = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyTopBlue1")),
				((TextureRegion) items.findRegion("duckFlyTopBlue2")),
				((TextureRegion) items.findRegion("duckFlyTopBlue3")));
		duckFlyTopBlack = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyTopBlack1")),
				((TextureRegion) items.findRegion("duckFlyTopBlack2")),
				((TextureRegion) items.findRegion("duckFlyTopBlack3")));
		duckFlyTopRed = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyTopRed1")),
				((TextureRegion) items.findRegion("duckFlyTopRed2")),
				((TextureRegion) items.findRegion("duckFlyTopRed3")));
		duckFlyUpBlue = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyUpBlue1")),
				((TextureRegion) items.findRegion("duckFlyUpBlue2")),
				((TextureRegion) items.findRegion("duckFlyUpBlue3")));
		duckFlyUpBlack = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyUpBlack1")),
				((TextureRegion) items.findRegion("duckFlyUpBlack2")),
				((TextureRegion) items.findRegion("duckFlyUpBlack3")));
		duckFlyUpRed = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyUpRed1")),
				((TextureRegion) items.findRegion("duckFlyUpRed2")),
				((TextureRegion) items.findRegion("duckFlyUpRed3")));
		uiDucks = new Animation(0.2f,
				((TextureRegion) items.findRegion("uiWhiteDuck")),
				((TextureRegion) items.findRegion("uiRedDuck")));

		duckFallingBlue = items.findRegion("duckFallingBlue");
		duckFallingBlack = items.findRegion("duckFallingBlack");
		duckFallingRed = items.findRegion("duckFallingRed");
		duckHitBlue = items.findRegion("duckHitBlue");
		duckHitBlack = items.findRegion("duckHitBlack");
		duckHitRed = items.findRegion("duckHitRed");

		ui0Shots = items.findRegion("ui0Shots1");
		duckHit = items.findRegion("duckHit");
		title = items.findRegion("title");
		gameMode1 = items.findRegion("gameMode1");
		gameMode2 = items.findRegion("gameMode2");
		menuCursor = items.findRegion("menuCursor");
		uiShot = items.findRegion("uiShot");
		uiDucksRound = items.findRegion("uiDucksRound");
		uiScore = items.findRegion("uiScore");
		presentRound = items.findRegion("presentRound");
		presentFlyAway = items.findRegion("presentFlyAway");
		ui3Shots = items.findRegion("ui3Shots");
		ui2Shots = items.findRegion("ui2Shots");
		ui1Shots = items.findRegion("ui1Shots");
		uiWhiteDuck = items.findRegion("uiWhiteDuck");
		uiRedDuck = items.findRegion("uiRedDuck");
		dogDuckFound = items.findRegion("dog1Duck");
		dogDucksFound = items.findRegion("dog2Ducks");
	}

	private static void loadFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/wonder.ttf"));
		font = generator.generateFont(20);
		generator.dispose();

	}

	private static void loadSounds() {
		duckHunt = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/DuckHunt.mp3"));
		duckHunt.setLooping(false);
		duckHunt.setVolume(0.5f);
		startRound = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/start_round.mp3"));
		endRound = Gdx.audio.newMusic((Gdx.files
				.internal("data/sounds/end_round.mp3")));
		gameOver1 = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/gameOver1.mp3"));
		gameOver2 = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/gameOver2.mp3"));

		shoot = Gdx.audio.newSound(Gdx.files.internal("data/sounds/blast.mp3"));
		dogBark = Gdx.audio
				.newSound(Gdx.files.internal("data/sounds/bark.mp3"));
		miss = Gdx.audio.newSound(Gdx.files.internal("data/sounds/miss.mp3"));
		dogLaughingSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/laugh.mp3"));
		dogDuckFoundSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/end_duck_round.mp3"));
		hitGround = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/drop.mp3"));
		duckFallingSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/duck_falling.mp3"));
		cuak = Gdx.audio.newSound(Gdx.files.internal("data/sounds/cuak.mp3"));
		movingDucksArray = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/movingDucksArray.mp3"));
		perfect = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/perfect.mp3"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
