package com.fabrizio.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fabrizio.flappybird.utils.Assets;

public class Main extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Assets.load();

        setScreen(new GameScreen(this));
    }


    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();

    }
}
