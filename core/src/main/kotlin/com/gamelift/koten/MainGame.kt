package com.gamelift.koten

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.FPSLogger
import com.gamelift.koten.classes.Assets
import com.gamelift.koten.classes.Settings
import com.gamelift.koten.screens.MainMenuScreen
import kotlin.properties

public class MainGame : Game() {
    var fps: FPSLogger? = null

    override fun create() {
        Settings.load()
        Assets.load()
        setScreen(MainMenuScreen(this))
        fps = FPSLogger()
    }

    override fun dispose() {
        super.dispose()
        getScreen().dispose()
    }

    override fun render() {
        super.render()
        fps!!.log()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }
}