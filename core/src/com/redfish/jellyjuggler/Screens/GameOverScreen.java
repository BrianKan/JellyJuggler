package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redfish.jellyjuggler.JellyJuggler;

/**
 * Created by Redfish on 7/18/2017.
 */

public class GameOverScreen implements Screen, InputProcessor {
    private JellyJuggler parent;
    private Stage stage;
    private Texture gameOverText;

    public GameOverScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new ScreenViewport());
        gameOverText=new Texture(Gdx.files.internal("gameOver.png"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.getBatch().begin();
        stage.getBatch().draw(parent.background,0,0,parent.SCREEN_WIDTH,parent.SCREEN_HEIGHT);
        stage.getBatch().draw(gameOverText,parent.SCREEN_WIDTH/2-parent.SCREEN_WIDTH/4.2f,parent.SCREEN_HEIGHT/2+parent.SCREEN_HEIGHT/8,parent.SCREEN_WIDTH/2,parent.SCREEN_HEIGHT/4.7f);
        stage.getBatch().end();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
        parent.switchScreen(0);
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
