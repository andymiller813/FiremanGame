package com.andytheandroidguy.firemangame.Screens;

import com.andytheandroidguy.firemangame.MainFireman;
import com.andytheandroidguy.firemangame.Scenes.Hud;
import com.andytheandroidguy.firemangame.Sprites.Fireman;
import com.andytheandroidguy.firemangame.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andy on 5/26/2016.
 */
public class PlayScreen implements Screen {

    private MainFireman mGame;
    private OrthographicCamera mGameCam;
    private Viewport mGamePort;
    private Hud mHud;

    private TmxMapLoader mMapLoader;
    private TiledMap mMap;
    private OrthogonalTiledMapRenderer mRenderer;

    private World mWorld;
    private Box2DDebugRenderer mB2dr;
    private Fireman mPlayer;

    public PlayScreen(MainFireman game) {
        mGame = game;
        mGameCam = new OrthographicCamera();
        mGamePort = new FitViewport(MainFireman.V_WIDTH / MainFireman.PPM, MainFireman.V_HEIGHT / MainFireman.PPM, mGameCam);
        mHud = new Hud(game.batch);

        mMapLoader = new TmxMapLoader();
        mMap = mMapLoader.load("level1.tmx");
        mRenderer = new OrthogonalTiledMapRenderer(mMap, 1 / MainFireman.PPM);

        mGameCam.position.set(mGamePort.getWorldWidth() / 2, mGamePort.getWorldHeight() / 2, 0);

        mWorld = new World(new Vector2(0, -10), true);
        mB2dr = new Box2DDebugRenderer();

        new B2WorldCreator(mWorld, mMap);

        mPlayer = new Fireman(mWorld);

    }

    @Override
    public void show() {

    }


    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            mPlayer.mb2Body.applyLinearImpulse(new Vector2(0, 4f), mPlayer.mb2Body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mPlayer.mb2Body.getLinearVelocity().x <= 2)
            mPlayer.mb2Body.applyLinearImpulse(new Vector2(0.1f, 0), mPlayer.mb2Body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mPlayer.mb2Body.getLinearVelocity().x >= -2)
            mPlayer.mb2Body.applyLinearImpulse(new Vector2(-0.1f, 0), mPlayer.mb2Body.getWorldCenter(), true);
    }


    /**
     * Check for any inputs and re-render.
     *
     * @param dt delta time.
     */
    public void update(float dt) {
        handleInput(dt);

        mWorld.step(1 / 60f, 6, 2);

        mGameCam.position.x = mPlayer.mb2Body.getPosition().x;

        mGameCam.update();
        mRenderer.setView(mGameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mRenderer.render();

        mB2dr.render(mWorld, mGameCam.combined);

        // Set our batch to now draw what the HUD camera sees.
        mGame.batch.setProjectionMatrix(mHud.mStage.getCamera().combined);
        mHud.mStage.draw();
    }


    @Override
    public void resize(int width, int height) {
        mGamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mMap.dispose();
        mRenderer.dispose();
        mWorld.dispose();
        mB2dr.dispose();
        mHud.dispose();
    }
}
