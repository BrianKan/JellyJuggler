package com.redfish.jellyjuggler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redfish.jellyjuggler.Screens.GameOverScreen;
import com.redfish.jellyjuggler.Screens.MenuScreen;
import com.redfish.jellyjuggler.Screens.PreferencesScreen;

public class JellyJuggler extends Game {
	//Screens
	private MenuScreen menuScreen;


	//App Preferences
	private AppPreferences preferences;
	//Get Screen Size
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;


    public static Texture background;


	@Override
	public void create () {
		SCREEN_WIDTH =Gdx.graphics.getWidth();
		SCREEN_HEIGHT=Gdx.graphics.getHeight();

		preferences = new AppPreferences();
        background=new Texture(Gdx.files.internal("bg.jpg"));

		menuScreen=new MenuScreen(this);
		setScreen(menuScreen);

	}

	// Method to do screen switching
	// 0 for menu, 1 for game, 2 for preferences, 3 for Game Over Screen
	public void switchScreen(int screen){
		switch(screen){
			case 0:
				this.setScreen(new MenuScreen(this));
				break;
			case 1:
//				this.setScreen(new PreferencesScreen(this));
				break;
			case 2:
				this.setScreen(new PreferencesScreen(this));
				break;
			case 3:
				this.setScreen(new GameOverScreen(this));
				break;

		}
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {


	}

	public AppPreferences getPreferences() {
		return preferences;
	}
}
