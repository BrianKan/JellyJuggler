package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redfish.jellyjuggler.JellyJuggler;

/**
 * Created by Redfish on 7/17/2017.
 */

public class MenuScreen implements Screen {
    private JellyJuggler parent;
    private Stage stage;
    private Texture background;
    private Texture title;

    public MenuScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Similar to HTML table for layout
        Table table=new Table();

        //Preset skin dl'ded from web
        Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        // Initialize Graphics
        background=new Texture(Gdx.files.internal("bg.jpg"));
        title=new Texture(Gdx.files.internal("title.png"));

        // Textbuttons
        TextButton newGame=new TextButton("New Game",skin);
        TextButton preferences=new TextButton("Preferences",skin);
        TextButton exit=new TextButton("Exit",skin);

        //Table Layout
        table.setFillParent(true);
        stage.addActor(table);
        // DEBUG PURPOSES
        table.setDebug(false);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fill().uniformX();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,480,800);
        stage.getBatch().draw(title,120,480,250,154);
        stage.getBatch().end();
        stage.draw();
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
