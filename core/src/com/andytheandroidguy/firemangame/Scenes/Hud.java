package com.andytheandroidguy.firemangame.Scenes;

import com.andytheandroidguy.firemangame.MainFireman;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andy on 5/26/2016.
 */
public class Hud implements Disposable {
    public Stage mStage;

    private Viewport mViewport;


    public Hud(SpriteBatch sb) {
        mViewport = new FitViewport(MainFireman.V_WIDTH, MainFireman.V_HEIGHT, new OrthographicCamera());
        mStage = new Stage(mViewport, sb);
    }

    @Override
    public void dispose() {
        mStage.dispose();
    }
}
