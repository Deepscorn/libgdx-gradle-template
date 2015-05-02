package com.gamelift.koten

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

public class MainGame : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
        newOne()
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch!!.begin()
        batch!!.draw(img, 0f, 0f)
        batch!!.end()
    }

    fun newOne() {
        println("a")
    }
}