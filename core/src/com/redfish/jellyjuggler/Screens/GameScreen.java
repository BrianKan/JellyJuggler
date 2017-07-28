package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Texture jellyTexture4;
    private BitmapFont font;
    private ArrayList<Body> bodies;
    private Random rng;

    //TODO Animation
    private static final int FRAME_COLS=1,FRAME_ROWS=6;
    private Animation<TextureRegion> animation;
    private Texture explosion;
    private SpriteBatch sb;
    private float spriteTime;
    private float animX;
    private float animY;

    private static final int FRAME_COLS1=6,FRAME_ROWS1=1;
    private Texture frostNova;
    private Animation<TextureRegion> frostAnimation;
    private float spriteTime2;


    //TODO Music
    private Sound frostSnd;
    private Sound explodeSnd;
    private Sound bounceSnd;

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
        jellyTexture4=new Texture(Gdx.files.internal("BombJelly.png"));
        testPoint=new Vector3(0,0,0);

        //TODO Sound Effects
        explodeSnd=Gdx.audio.newSound(Gdx.files.internal("explodeBomb.wav"));
        frostSnd=Gdx.audio.newSound(Gdx.files.internal("iceNova.wav"));

        animX=0;
        animY=0;
        //TODO Animation
        explosion=new Texture(Gdx.files.internal("poof.png"));
        TextureRegion[][] tmp=TextureRegion.split(explosion,explosion.getWidth(),explosion.getHeight()/FRAME_ROWS);
        TextureRegion[] explodeAnim=new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index=0;
        for(int i =0;i<FRAME_ROWS;i++){
            for(int j=0;j<FRAME_COLS;j++){
                explodeAnim[index++]=tmp[i][j];
            }
        }
        animation=new Animation<TextureRegion>(0.10f,explodeAnim);
        spriteTime=0.6f;

        frostNova=new Texture(Gdx.files.internal("frostNova.png"));
        TextureRegion[][] tmp1=TextureRegion.split(frostNova,frostNova.getWidth()/FRAME_COLS1,frostNova.getHeight()/FRAME_ROWS1);
        TextureRegion[] frostAnim=new TextureRegion[FRAME_COLS1*FRAME_ROWS1];
        int index1=0;
        for(int i =0;i<FRAME_ROWS1;i++){
            for(int j=0;j<FRAME_COLS1;j++){
                frostAnim[index1++]=tmp1[i][j];
            }
        }
        frostAnimation=new Animation<TextureRegion>(0.10f,frostAnim);
        spriteTime2=0.6f;
    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TODO Animation
        if(!animation.isAnimationFinished(spriteTime)) {
            spriteTime += Gdx.graphics.getDeltaTime();
        }
        if(!animation.isAnimationFinished(spriteTime2)) {
            spriteTime2 += Gdx.graphics.getDeltaTime();
        }

        if(parent.playerScore==0){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,0));
        }
        else if(parent.playerScore==10){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,2));
        }
        else if(parent.playerScore==20){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,0));
        }
        else if(parent.playerScore==30){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,3));
        }
        else if(parent.playerScore==40){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,3));
        }
        else if(parent.playerScore==50){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,1));
        }
        else if(parent.playerScore==50){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,1));
        }
        else if(parent.playerScore%10==0){
            parent.playerScore++;
            bodies.add(bodyFactory.makeBoxPolyBody(1,15,6,6,BodyFactory.WOOD, BodyDef.BodyType.DynamicBody,rng.nextInt(4)));
        }


        if(model.gameOver){
            model.gameOver=false;
            parent.switchScreen(3);
        }
        stage.getBatch().begin();
        stage.getBatch().draw(parent.background,0,0,parent.SCREEN_WIDTH,parent.SCREEN_HEIGHT);
        stage.getBatch().end();

        //TODO Animation
        TextureRegion currentFrame=animation.getKeyFrame(spriteTime,true);
        TextureRegion currentFrame1=frostAnimation.getKeyFrame(spriteTime2,true);

        sb.begin();
        font.getData().setScale(0.3f);
        font.draw(sb,parent.playerScore.toString(),-2,23);
        for(Body bod:bodies){
            sb.draw(randomJelly(bod),bod.getPosition().x-3, bod.getPosition().y-3,6,6);
        }
        if(!frostAnimation.isAnimationFinished(spriteTime2)){
            sb.draw(currentFrame1, animX, animY, 25, 25);
        }
        if(!animation.isAnimationFinished(spriteTime)) {
            sb.draw(currentFrame, animX, animY, 25, 25);
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
        else if(body.getUserData().equals(3))
            return jellyTexture4;
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
                                      if(fixture.getBody().getType()== BodyDef.BodyType.StaticBody){
                                          return false;
                                      }
                                      hitBody = fixture.getBody();
                                      hitBody.setLinearVelocity(0, 0);
                                      hitBody.applyForceToCenter(new Vector2(rng.nextInt(20000) - 10000, 30000), true);
                                      parent.playerScore++;

                                      if (hitBody.getUserData().equals(1)) {
                                          // Playing the sound
                                          if(parent.getPreferences().isSoundEnabled())
                                              frostSnd.play();
                                          bodies.remove(hitBody);
                                          animX=hitBody.getPosition().x-12.5f;
                                          animY=hitBody.getPosition().y-12.5f;
                                          spriteTime2=0;
                                          model.world.destroyBody(hitBody);
                                          for(Body bod: bodies ){
                                              bod.setLinearVelocity(0,0);
                                          }
                                      }
                                      else
                                      if (hitBody.getUserData().equals(3)) {
                                          if(parent.getPreferences().isSoundEnabled())
                                              explodeSnd.play();
                                          bodies.remove(hitBody);
                                          animX=hitBody.getPosition().x-12.5f;
                                          animY=hitBody.getPosition().y-12.5f;
                                          spriteTime=0;
                                          model.world.destroyBody(hitBody);
                                           for(Body bod: bodies ){
                                                applyBlastImpulse(bod,hitBody.getPosition(),bod.getPosition(),20000);
                                            }
                                      }
                                      return false;
                                  }
                              }
                , testPoint.x - 0.1f, testPoint.y - 0.1f, testPoint.x + 0.1f, testPoint.y + 0.1f);
        return false;
    }
// from github https://github.com/polluxx/git_repo/blob/master/Mass/src/com/mass/WorldRenderer.java
    public void applyBlastImpulse(Body body,Vector2 blastCenter,Vector2 applyPoint,int blastPower){
        Vector2 blastDir;
        float distance;

        if (body.getType()!= BodyDef.BodyType.DynamicBody){
            return;
        }

        blastDir = new Vector2(applyPoint.x-blastCenter.x,applyPoint.y- blastCenter.y);
        distance = blastCenter.dst(applyPoint);
        float invDistance=1/distance;
        if(distance==0){
            return;
        }
        float impulseMag =blastPower*invDistance*invDistance;
        Vector2 newImpulse=new Vector2(impulseMag*blastDir.x,impulseMag*blastDir.y);
        body.applyLinearImpulse(newImpulse,applyPoint,true);
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

