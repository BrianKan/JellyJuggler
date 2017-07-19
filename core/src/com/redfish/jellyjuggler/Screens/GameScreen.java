package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redfish.jellyjuggler.JellyJuggler;

/**
 * Created by Redfish on 7/18/2017.
 */

public class GameScreen implements Screen {
    private JellyJuggler parent;
    private Stage stage;

    public GameScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new ScreenViewport());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
}

