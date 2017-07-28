package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redfish.jellyjuggler.JellyJuggler;

/**
 * Created by Redfish on 7/17/2017.
 */

public class MenuScreen implements Screen {
    private JellyJuggler parent;
    private Stage stage;
    private Texture title;


    public MenuScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new StretchViewport(480,800));

        // TODO Animation 2
//        sb= new SpriteBatch();
//        textureAtlas=new TextureAtlas(Gdx.files.internal("leaves.png"));
//        leafAnimation=new Animation(1/10f,textureAtlas.getRegions());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);


        Table table=new Table();
        Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        title=new Texture(Gdx.files.internal("title.png"));

        if(parent.getPreferences().isAdsEnabled()){
            parent.adsController.showBannerAd();
        }

        TextButton newGame=new TextButton("New Game",skin);
        TextButton preferences=new TextButton("Preferences",skin);
        TextButton exit=new TextButton("Exit",skin);
        Label empty = new Label("",skin);

        table.setFillParent(true);
        stage.addActor(table);
//
//        table.add(empty).height(parent.SCREEN_WIDTH/15);
//        table.row().pad(10,0,parent.SCREEN_HEIGHT/75,0);
//        table.add(newGame).width(parent.SCREEN_WIDTH/2).height(parent.SCREEN_HEIGHT/17);
//        table.row().pad(10,0,parent.SCREEN_HEIGHT/75,0);
//        table.add(preferences).fillX().uniformX().height(parent.SCREEN_HEIGHT/17);
//        table.row().pad(10,0,parent.SCREEN_HEIGHT/75,0);
//        table.add(exit).fill().uniformX().height(parent.SCREEN_HEIGHT/17);


        table.add(newGame).width(240);
        table.row().pad(10,0,10,0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fill().uniformX();

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchScreen(1);
            }
        });
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchScreen(2);
            }
        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        stage.getBatch().begin();
        stage.getBatch().draw(parent.background, 0, 0, 480, 800);
        stage.getBatch().draw(title, 120, 480, 250, 154);
//        stage.getBatch().draw(title,parent.SCREEN_WIDTH/2-parent.SCREEN_WIDTH/4.2f,parent.SCREEN_HEIGHT/2+parent.SCREEN_HEIGHT/8,parent.SCREEN_WIDTH/2,parent.SCREEN_HEIGHT/4.7f);
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
