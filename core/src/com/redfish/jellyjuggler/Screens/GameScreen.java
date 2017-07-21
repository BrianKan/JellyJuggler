package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redfish.jellyjuggler.BodyFactory;
import com.redfish.jellyjuggler.JellyJuggler;
import com.redfish.jellyjuggler.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Redfish on 7/18/2017.
 */

public class GameScreen implements Screen,InputProcessor {
    private JellyJuggler parent;
    private Model model;
    private Stage stage;
    private Camera cam;
    private BodyFactory bodyFactory;
    private Body hitBody;
    private Vector3 testPoint;
    private Texture jellyTexture;
    private Texture jellyTexture2;
    private Texture jellyTexture3;
    private SpriteBatch sb;
    private BitmapFont font;
    private ArrayList<Body> bodies;
    private Random rng;

    public GameScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new ScreenViewport());
        model = new Model();
        cam = new OrthographicCamera(50, 64);
        stage = new Stage(new ScreenViewport());
        sb=new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);
        font=new BitmapFont();
        parent.playerScore=0;
        rng=new Random();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        bodies=new ArrayList<Body>();
        bodyFactory=new BodyFactory(model.world);
        jellyTexture=new Texture(Gdx.files.internal("GreenJelly.png"));
        jellyTexture2=new Texture(Gdx.files.internal("BlueJelly.png"));
        jellyTexture3=new Texture(Gdx.files.internal("OrangeJelly.png"));
        testPoint=new Vector3(0,0,0);

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(parent.playerScore%10==0){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody));
        }
        if(model.gameOver){
            model.gameOver=false;
            parent.switchScreen(3);
        }
        stage.getBatch().begin();
        stage.getBatch().draw(parent.background,0,0,parent.SCREEN_WIDTH,parent.SCREEN_HEIGHT);

        stage.getBatch().end();
        sb.begin();
        font.getData().setScale(0.3f);
        font.draw(sb,parent.playerScore.toString(),-2,23);
        for(Body bod:bodies){
            sb.draw(randomJelly(bod),bod.getPosition().x-3, bod.getPosition().y-3,6,6);
        }
        sb.end();
    }

    @Override
    public void resize(int width, int height) {stage.getViewport().update(width,height,true);

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

    }

    public Texture randomJelly(Body body){
        if (body.getUserData().equals(0))
            return jellyTexture;
        else if (body.getUserData().equals(1))
            return jellyTexture2;
        else if(body.getUserData().equals(2))
            return jellyTexture3;
        else
            return jellyTexture;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        testPoint.set(screenX, screenY, 0);
        cam.unproject(testPoint);
        hitBody = null;
        model.world.QueryAABB(new QueryCallback() {
                                  @Override
                                  public boolean reportFixture(Fixture fixture) {
                                      hitBody = fixture.getBody();
                                      hitBody.setLinearVelocity(0,0);
                                      hitBody.applyForceToCenter(new Vector2(rng.nextInt(20000)-10000, 30000), true);
                                      parent.playerScore++;
                                      return true;
                                  }
                              }
                , testPoint.x - 0.1f, testPoint.y - 0.1f, testPoint.x + 0.1f, testPoint.y + 0.1f);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

