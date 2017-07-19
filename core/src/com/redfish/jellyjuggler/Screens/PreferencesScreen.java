package com.redfish.jellyjuggler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redfish.jellyjuggler.JellyJuggler;

/**
 * Created by Redfish on 7/18/2017.
 */

public class PreferencesScreen implements Screen {

    private JellyJuggler parent;
    private Stage stage;

    private Label title;
    private Label sounds;
    private Label music;

    public PreferencesScreen(JellyJuggler parent){
        this.parent=parent;
        stage=new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table= new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin= new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        title = new Label("Preferences", skin);
        sounds = new Label("Sounds", skin);
        music = new Label("Music", skin);

        final TextButton backButton = new TextButton("Back", skin);
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);


        musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled());
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().enableMusic( enabled );
                return false;
            }
        });

        soundEffectsCheckbox.setChecked( parent.getPreferences().isSoundEnabled());
        soundEffectsCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().enableSounds( enabled );
                return false;
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchScreen(0);
            }
        });

        table.add(title);
        table.row();
        table.add(music);
        table.add(musicCheckbox);
        table.row();
        table.add(sounds);
        table.add(soundEffectsCheckbox);
        table.row();
        table.add(backButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(parent.background,0,0,parent.SCREEN_WIDTH,parent.SCREEN_HEIGHT);
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
