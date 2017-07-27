package com.redfish.jellyjuggler;

/**
 * Created by Redfish on 7/17/2017.
 */

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Model {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body bodyd;
    public static boolean gameOver;

    public Model(){
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new ContactListeners(this));


        createFloor(-25);
        createCeiling(37);
        createWalls(-25);
        createWalls(25);

        BodyFactory bodyFactory = BodyFactory.getInstance(world);



    }


    private void createCeiling(int x) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, x);


        bodyd = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 3);


        bodyd.createFixture(shape, 0.0f);

        shape.dispose();
    }
    private void createFloor(int x) {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, x);


        bodyd = world.createBody(bodyDef);
        bodyd.setUserData("hello");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 3);


        bodyd.createFixture(shape, 0.0f);

        shape.dispose();
    }

    private void createWalls(int x) {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, -10);


        bodyd = world.createBody(bodyDef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 100);


        bodyd.createFixture(shape, 0.0f);


        shape.dispose();
    }

    public void logicStep(float delta){

        world.step(delta , 3, 3);
    }
}