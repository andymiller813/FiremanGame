package com.andytheandroidguy.firemangame.Sprites;

import com.andytheandroidguy.firemangame.MainFireman;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Andy on 7/11/2016.
 */
public class Fireman extends Sprite {
    public World mWorld;
    public Body mb2Body;

    public Fireman(World world) {
        this.mWorld = world;
        defineFireman();
    }


    public void defineFireman() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MainFireman.PPM, 32 / MainFireman.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        mb2Body = mWorld.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MainFireman.PPM);
        fixtureDef.shape = shape;
        mb2Body.createFixture(fixtureDef);
    }
}
